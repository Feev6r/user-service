package dev.ferv.user_service.application.handler.interfaces;

import dev.ferv.user_service.application.dto.request.ClientRequest;
import dev.ferv.user_service.application.dto.request.EmployeeRequest;
import dev.ferv.user_service.application.dto.request.OwnerRequest;
import dev.ferv.user_service.application.dto.request.UserAuthenticationRequest;
import dev.ferv.user_service.application.dto.response.AuthenticationResponse;

public interface IAuthenticationHandler {

    AuthenticationResponse registerOwner(OwnerRequest registerRequest);
    AuthenticationResponse registerEmployee(EmployeeRequest registerRequest);
    AuthenticationResponse registerClient(ClientRequest registerRequest);
    AuthenticationResponse registerAdmin(OwnerRequest registerRequest);
    AuthenticationResponse authenticate(UserAuthenticationRequest user);


}
