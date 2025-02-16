package dev.ferv.user_service.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationRequest {

    private String email;
    private String password;
            
}
