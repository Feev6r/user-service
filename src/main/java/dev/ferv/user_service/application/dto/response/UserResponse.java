package dev.ferv.user_service.application.dto.response;

import dev.ferv.user_service.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {

    private Role role;
    private Long id;
    private String firstname;
    private String lastname;
    private String identityNumber;
    private String phoneNumber;
    private String birthdate;
    private String email;
}
