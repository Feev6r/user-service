package dev.ferv.user_service.infrastructure.input;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import dev.ferv.user_service.application.dto.request.ClientRequest;
import dev.ferv.user_service.application.dto.request.EmployeeRequest;
import dev.ferv.user_service.application.dto.request.OwnerRequest;
import dev.ferv.user_service.application.dto.request.UserAuthenticationRequest;
import dev.ferv.user_service.application.dto.response.AuthenticationResponse;
import dev.ferv.user_service.application.handler.interfaces.IAuthenticationHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthentificationController {

    private final IAuthenticationHandler authenticationHandler;

    @PostMapping("/registerOwner")
    public ResponseEntity<AuthenticationResponse> registerOwner(@Valid @RequestBody OwnerRequest registerRequest) {
        return ResponseEntity.ok(authenticationHandler.registerOwner(registerRequest));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody OwnerRequest registerRequest) {
        return ResponseEntity.ok(authenticationHandler.registerAdmin(registerRequest));
    }
    
    @PostMapping("/registerEmployee")
    public ResponseEntity<AuthenticationResponse> registerEmployee(@RequestBody EmployeeRequest registerRequest) {
        return ResponseEntity.ok(authenticationHandler.registerEmployee(registerRequest));
    }
    @PostMapping("/registerClient")
    public ResponseEntity<AuthenticationResponse> registerClient(@RequestBody ClientRequest registerRequest) {
        return ResponseEntity.ok(authenticationHandler.registerClient(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserAuthenticationRequest userRequest) {
        return ResponseEntity.ok(authenticationHandler.authenticate(userRequest));
    }
    
}
