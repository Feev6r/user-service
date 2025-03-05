package dev.ferv.user_service.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContactInformationResponse { 
    private String firstname;
    private String phoneNumber;
    private String email;
}
