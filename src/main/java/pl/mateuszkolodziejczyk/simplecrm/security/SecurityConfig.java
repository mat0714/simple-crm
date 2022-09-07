package pl.mateuszkolodziejczyk.simplecrm.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.mateuszkolodziejczyk.simplecrm.exception.ExceptionSupplier;
import pl.mateuszkolodziejczyk.simplecrm.security.jwt.JwtConfig;
import pl.mateuszkolodziejczyk.simplecrm.security.jwt.JwtAuthenticationFilter;
import pl.mateuszkolodziejczyk.simplecrm.security.jwt.JwtProvider;
import pl.mateuszkolodziejczyk.simplecrm.user.repository.UserRepository;

import static pl.mateuszkolodziejczyk.simplecrm.user.UserRole.*;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtConfig jwtConfig;
    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String[] paths = {"/api/companies/**", "/api/customers/**", "/api/employees/**", "/api/events/**"};

        http
                .csrf().disable()
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling(c-> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .authorizeRequests()
                    .antMatchers("/console/**").permitAll()
                    .antMatchers("/api/login").permitAll()
                    .antMatchers(HttpMethod.GET,paths).hasAnyRole(MANAGER.name(), EMPLOYEE.name())
                    .antMatchers(HttpMethod.POST ,paths).hasAnyRole(MANAGER.name(), EMPLOYEE.name())
                    .antMatchers(HttpMethod.PUT, paths).hasAnyRole(MANAGER.name(), EMPLOYEE.name())
                    .antMatchers(HttpMethod.DELETE ,paths).hasRole(MANAGER.name())
                    .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtConfig, jwtProvider), UsernamePasswordAuthenticationFilter.class)
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

    @Bean
    AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        AuthenticationManager authenticationManager = authentication -> {
            String username = authentication.getPrincipal() + "";
            String password = authentication.getCredentials() + "";

            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (!encoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Bad credentials");
            }

            if (!user.isEnabled()) {
                throw new DisabledException("User account is not active");
            }

            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        };
        return authenticationManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
