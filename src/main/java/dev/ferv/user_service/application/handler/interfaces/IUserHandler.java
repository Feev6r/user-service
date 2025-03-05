package dev.ferv.user_service.application.handler.interfaces;

import java.util.List;

import dev.ferv.user_service.application.dto.response.UserContactInformationResponse;
import dev.ferv.user_service.application.dto.response.UserResponse;

public interface IUserHandler {


    UserResponse getUser(); //usuario personal
    List<UserResponse> getAllUser(); //todo los usuarios - solo admin
    UserResponse getUserById(Long id);
    UserContactInformationResponse getUserContact(Long id);
}
