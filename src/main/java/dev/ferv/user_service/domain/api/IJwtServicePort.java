package dev.ferv.user_service.domain.api;

import dev.ferv.user_service.domain.model.User;

public interface IJwtServicePort {

    String extractUsername(String token);
    String getUserEmailBySecurityContext();
    String generateToken(User user);
}
