package dev.ferv.user_service.domain.usecase;

import java.util.List;

import dev.ferv.user_service.domain.api.IUserServicePort;
import dev.ferv.user_service.domain.model.User;
import dev.ferv.user_service.domain.spi.IUserPersistancePort;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistancePort userPersistancePort;    
 
    public UserUseCase(IUserPersistancePort userPersistancePort) {
        this.userPersistancePort = userPersistancePort;
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userPersistancePort.getUserByEmail(email);
    }

    @Override
    public User getUser(Long userId) {
        return userPersistancePort.getUser(userId);
    }

    @Override
    public void saveUser(User user) {
        userPersistancePort.saveUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userPersistancePort.getAllUser();
    }

}
