package pl.mateuszkolodziejczyk.simplecrm.user.api.request;

import lombok.Getter;

@Getter
public class AuthenticationRequest {

    private String username;
    private String password;
}