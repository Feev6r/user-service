package dev.ferv.user_service.domain.usecase;

import dev.ferv.user_service.domain.api.IJwtServicePort;
import dev.ferv.user_service.domain.model.User;
import dev.ferv.user_service.domain.spi.IJwtPort;

public class JwtUseCase implements IJwtServicePort{

    private final IJwtPort jwtPort;
    
    public JwtUseCase(IJwtPort jwtServicePort){
        this.jwtPort = jwtServicePort;
    }


    @Override
    public String getUserEmailBySecurityContext() {
        return jwtPort.getUserEmailBySecurityContext();
    }
    
    @Override
    public String extractUsername(String token) {
       return jwtPort.extractUsername(token);
    }

    @Override
    public String generateToken(User user) {
       return jwtPort.generateToken(user);
    }

}
