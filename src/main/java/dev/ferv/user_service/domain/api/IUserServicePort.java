package dev.ferv.user_service.domain.api;

import java.util.List;

import dev.ferv.user_service.domain.model.User;

public interface IUserServicePort {

    User getUserByEmail(String email);
    User getUser(Long userId); //usuario personal
    void saveUser(User user);
    List<User> getAllUser(); //todo los usuarios - solo admin
}
