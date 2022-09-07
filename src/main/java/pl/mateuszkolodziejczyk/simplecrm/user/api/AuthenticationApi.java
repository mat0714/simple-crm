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
import pl.mateuszkolodziejczyk.simplecrm.security.jwt.JwtProvider;
import pl.mateuszkolodziejczyk.simplecrm.user.api.request.AuthenticationRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationApi {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody AuthenticationRequest authenticationRequest) {

        try {
            String username = authenticationRequest.getUsername();
            String password = authenticationRequest.getPassword();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtProvider.createToken(authentication);
            HttpHeaders header = new HttpHeaders();
            header.set(HttpHeaders.AUTHORIZATION, jwtConfig.getTokenPrefix() + token);
            return ResponseEntity.status(HttpStatus.OK).headers(header).build();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password.");
        }
    }
}
