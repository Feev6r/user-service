package dev.ferv.user_service.application.handler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.ferv.user_service.application.dto.request.ClientRequest;
import dev.ferv.user_service.application.dto.request.EmployeeRequest;
import dev.ferv.user_service.application.dto.request.OwnerRequest;
import dev.ferv.user_service.application.dto.request.UserAuthenticationRequest;
import dev.ferv.user_service.application.dto.response.AuthenticationResponse;
import dev.ferv.user_service.application.handler.interfaces.IAuthenticationHandler;
import dev.ferv.user_service.application.mapper.UserRequestMapper;
import dev.ferv.user_service.domain.api.IJwtServicePort;
import dev.ferv.user_service.domain.api.IUserServicePort;
import dev.ferv.user_service.domain.model.Role;
import dev.ferv.user_service.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationHandler implements IAuthenticationHandler{

    private final IJwtServicePort jwtServicePort;
    private final IUserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    
    @Override
    public AuthenticationResponse registerOwner(OwnerRequest registerRequest) {
        User user = userRequestMapper.toUser(registerRequest);      
        return register(user, Role.OWNER);
    }

    @Override
    public AuthenticationResponse registerEmployee(EmployeeRequest registerRequest) {
        User user = userRequestMapper.toUser(registerRequest);
        return register(user, Role.EMPLOYEE);
    }

    @Override
    public AuthenticationResponse registerClient(ClientRequest registerRequest) {
        User user = userRequestMapper.toUser(registerRequest);
        return register(user, Role.CLIENT);
    }


    @Override
    public AuthenticationResponse registerAdmin(OwnerRequest registerRequest) {
        User user = userRequestMapper.toUser(registerRequest);
        return register(user, Role.ADMIN);
    }

    
    private AuthenticationResponse register(User user, Role role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);

        if(role == Role.OWNER){
            LocalDate birhtDate = LocalDate.parse(user.getBirthdate(), DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate minDate = LocalDate.now().minusYears(18);

            if (!birhtDate.isBefore(minDate) && !birhtDate.isEqual(minDate)) {
                throw new RuntimeException("The user has to be at leat 18 years old");
            }
        }

        userServicePort.saveUser(user);
        String jwtToken = jwtServicePort.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    @Override
    public AuthenticationResponse authenticate(UserAuthenticationRequest userRequest) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userRequest.getEmail(),
                userRequest.getPassword())
            );

        User user = userServicePort.getUserByEmail(userRequest.getEmail());
        String jwtToken = jwtServicePort.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }


}
