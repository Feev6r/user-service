package dev.ferv.user_service.infrastructure.input;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ferv.user_service.application.dto.response.UserContactInformationResponse;
import dev.ferv.user_service.application.dto.response.UserResponse;
import dev.ferv.user_service.application.handler.interfaces.IUserHandler;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final IUserHandler userHandler;

    @Operation(
        description = "get endpoint for the users",
        summary = "get users",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            ),
            @ApiResponse(
                description = "Unauthorized / invalid token",
                responseCode = "403"
            )
        }
    )
    @GetMapping("get")
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(userHandler.getUser());
    }
    

    @Operation(
        description = "obtains complete information about a user",
        summary = "get users by id",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            ),
            @ApiResponse(
                description = "Unauthorized / invalid token",
                responseCode = "403"
            )
        }
    )
    @GetMapping("get/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getUserById(id));
    }

    @Operation(
        description = "obtains contact information about a user ",
        summary = "get users contact by id",
        responses = {
            @ApiResponse(
                description = "Succes",
                responseCode = "200"
            ),
            @ApiResponse(
                description = "Unauthorized / invalid token",
                responseCode = "403"
            )
        }
    )
    @GetMapping("getContact/{id}")
    public ResponseEntity<UserContactInformationResponse> getContactInfo(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.getUserContact(id));

    }
    

    @Hidden 
    @GetMapping("getAll") //only admin
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userHandler.getAllUser());
    }
    
}
