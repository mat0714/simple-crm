package pl.mateuszkolodziejczyk.simplecrm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static pl.mateuszkolodziejczyk.simplecrm.security.ApplicationUserRole.*;

@Configuration
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/employees").hasRole(MANAGER.name())
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
