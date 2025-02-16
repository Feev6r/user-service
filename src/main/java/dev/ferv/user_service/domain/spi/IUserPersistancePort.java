package dev.ferv.user_service.domain.spi;

import java.util.List;

import dev.ferv.user_service.domain.model.User;

public interface IUserPersistancePort {
    
    User getUserByEmail(String email);
    void saveUser(User user);
    User getUser(Long userId); //usuario personal
    List<User> getAllUser(); //todo los usuarios - solo admin
}
