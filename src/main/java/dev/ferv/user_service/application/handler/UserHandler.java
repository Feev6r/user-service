package dev.ferv.user_service.application.handler;

import java.util.List;

import dev.ferv.user_service.application.dto.response.UserResponse;
import dev.ferv.user_service.application.handler.interfaces.IUserHandler;
import dev.ferv.user_service.application.mapper.UserResponseMapper;
import dev.ferv.user_service.domain.api.IJwtServicePort;
import dev.ferv.user_service.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserHandler implements IUserHandler{


    private final IUserServicePort userServicePort;
    private final UserResponseMapper userResponseMapper;
    private final IJwtServicePort jwtServicePort;

    @Override
    public UserResponse getUser() {
        String email = jwtServicePort.getUserEmailBySecurityContext();
        return userResponseMapper.toResponse(userServicePort.getUserByEmail(email));
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userResponseMapper.toResponseList(userServicePort.getAllUser());
    }

}
