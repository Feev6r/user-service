package dev.ferv.user_service.infrastructure.input;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ferv.user_service.application.dto.response.UserResponse;
import dev.ferv.user_service.application.handler.interfaces.IUserHandler;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;

    @GetMapping("get")
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(userHandler.getUser());
    }
    
    @GetMapping("getAll") //only admin
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userHandler.getAllUser());
    }
    
}
