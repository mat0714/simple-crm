package pl.mateuszkolodziejczyk.simplecrm.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.mateuszkolodziejczyk.simplecrm.exception.ExceptionSupplier;
import pl.mateuszkolodziejczyk.simplecrm.user.repository.UserRepository;

import static pl.mateuszkolodziejczyk.simplecrm.user.UserRole.*;

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
    UserDetailsService customUserDetailsService(UserRepository userRepository) {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username)
                        .orElseThrow(ExceptionSupplier.userNotFound(username));
            }
        };
    }
}
