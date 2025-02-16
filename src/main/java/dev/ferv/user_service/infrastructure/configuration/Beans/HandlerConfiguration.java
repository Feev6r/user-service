package dev.ferv.user_service.infrastructure.configuration.Beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.ferv.user_service.application.handler.AuthenticationHandler;
import dev.ferv.user_service.application.handler.UserHandler;
import dev.ferv.user_service.application.handler.interfaces.IAuthenticationHandler;
import dev.ferv.user_service.application.handler.interfaces.IUserHandler;
import dev.ferv.user_service.application.mapper.UserRequestMapper;
import dev.ferv.user_service.application.mapper.UserResponseMapper;
import dev.ferv.user_service.domain.api.IJwtServicePort;
import dev.ferv.user_service.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class HandlerConfiguration {

    private final IJwtServicePort jwtServicePort;
    private final IUserServicePort userServicePort;
    private final UserRequestMapper registerRequestMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserResponseMapper userResponseMapper;

    @Bean
    IAuthenticationHandler iAuthenticationHandler(){
        return new AuthenticationHandler(jwtServicePort, userServicePort, registerRequestMapper, authenticationManager, passwordEncoder);
    }

    @Bean
    IUserHandler iUserHandler(){
        return new UserHandler(userServicePort, userResponseMapper, jwtServicePort);
    }

}
