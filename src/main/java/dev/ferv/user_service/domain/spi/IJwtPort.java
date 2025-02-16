package dev.ferv.user_service.domain.spi;

import dev.ferv.user_service.domain.model.User;

public interface IJwtPort {

    String extractUsername(String token);
    String getUserEmailBySecurityContext();
    String generateToken(User user);
    boolean isTokenValid(String token, String username);
}
