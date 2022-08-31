package pl.mateuszkolodziejczyk.simplecrm.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mateuszkolodziejczyk.simplecrm.security.jwt.JwtConfig;
import pl.mateuszkolodziejczyk.simplecrm.security.jwt.JwtTokenProvider;
import pl.mateuszkolodziejczyk.simplecrm.user.api.request.UsernameAndPasswordAuthenticationRequest;
import pl.mateuszkolodziejczyk.simplecrm.user.repository.UserRepository;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest) {

        try {
            String username = usernameAndPasswordAuthenticationRequest.getUsername();
            String password = usernameAndPasswordAuthenticationRequest.getPassword();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtTokenProvider.createToken(authentication);
            HttpHeaders header = new HttpHeaders();
            header.set(HttpHeaders.AUTHORIZATION, jwtConfig.getTokenPrefix() + token);
            return ResponseEntity.status(HttpStatus.OK).headers(header).build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password.");
        }
    }
}
