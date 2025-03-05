package dev.ferv.user_service.infrastructure.input;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import dev.ferv.user_service.application.dto.request.ClientRequest;
import dev.ferv.user_service.application.dto.request.EmployeeRequest;
import dev.ferv.user_service.application.dto.request.OwnerRequest;
import dev.ferv.user_service.application.dto.request.UserAuthenticationRequest;
import dev.ferv.user_service.application.dto.response.AuthenticationResponse;
import dev.ferv.user_service.application.handler.interfaces.IAuthenticationHandler;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
public class AuthentificationController {

    private final IAuthenticationHandler authenticationHandler;


    @Operation(
        description = "register an owner user",
        summary = "register owner",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            )
        }
    )
    @PostMapping("/registerOwner")
    public ResponseEntity<AuthenticationResponse> registerOwner(@Valid @RequestBody OwnerRequest registerRequest) {
        return ResponseEntity.ok(authenticationHandler.registerOwner(registerRequest));
    }

    
    @Operation(
        description = "register an admin user",
        summary = "register admin",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            )
        }
    )
    @Hidden
    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody OwnerRequest registerRequest) {
        return ResponseEntity.ok(authenticationHandler.registerAdmin(registerRequest));
    }
    
    
    @Operation(
        description = "register an employee user",
        summary = "register employee",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            )
        }
    )
    @PostMapping("/registerEmployee")
    public ResponseEntity<AuthenticationResponse> registerEmployee(@RequestBody EmployeeRequest registerRequest) {
        return ResponseEntity.ok(authenticationHandler.registerEmployee(registerRequest));
    }

    @Operation(
        description = "register a client user",
        summary = "register client",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            )
        }
    )
    @PostMapping("/registerClient")
    public ResponseEntity<AuthenticationResponse> registerClient(@RequestBody ClientRequest registerRequest) {
        return ResponseEntity.ok(authenticationHandler.registerClient(registerRequest));
    }

    @Operation(
        description = "authenticates any user and obtain a token",
        summary = "authenticate",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            ),
            @ApiResponse(
                description = "Unauthorized / invalid user",
                responseCode = "401"
            )
        }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserAuthenticationRequest userRequest) {
        return ResponseEntity.ok(authenticationHandler.authenticate(userRequest));
    }
    
}
