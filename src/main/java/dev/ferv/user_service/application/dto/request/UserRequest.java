package dev.ferv.user_service.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotNull
    @Size(min = 3, message = "firstname has to be almost 3 characters long")
    private String firstname;
    @NotNull
    @Size(min = 3, message = "lastname has to be almost 3 characters long")
    private String lastname;
    @NotNull
    private Long identityNumber;

    @NotBlank
    @Pattern(regexp = "^(?:(?:\\+57|57)?(?:60[1-8]|3[0-9]{2})[0-9]{7})$", 
    message = "Not valid phone number")
    private String phoneNumber;
    
    @Email(message = "Invalid email")
    private String email; 
    @NotNull
    private String password;
}
