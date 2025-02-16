package dev.ferv.user_service.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerRequest extends UserRequest{

    @NotNull
    private String birthdate;  

}
