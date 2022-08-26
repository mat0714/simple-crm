package pl.mateuszkolodziejczyk.simplecrm.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static pl.mateuszkolodziejczyk.simplecrm.security.ApplicationUserRole.*;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String[] paths = {"/api/companies/**", "/api/customers/**", "/api/employees/**", "/api/events/**"};

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .antMatchers(HttpMethod.GET,paths).hasAnyRole(MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.POST ,paths).hasAnyRole(MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.PUT, paths).hasAnyRole(MANAGER.name(), EMPLOYEE.name())
                .antMatchers(HttpMethod.DELETE ,paths).hasRole(MANAGER.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails manager = User.builder()
                .username("Manager")
                .password(passwordEncoder.encode("Password123"))
                .roles(MANAGER.name())
                .build();

        UserDetails employee = User.builder()
                .username("employee")
                .password(passwordEncoder.encode("Password123"))
                .roles(EMPLOYEE.name())
                .build();
        return new InMemoryUserDetailsManager(manager, employee);
    }
}
