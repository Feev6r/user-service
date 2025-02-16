package dev.ferv.user_service.infrastructure.output.jpa.adapter;

import java.util.List;

import dev.ferv.user_service.domain.model.User;
import dev.ferv.user_service.domain.spi.IUserPersistancePort;
import dev.ferv.user_service.infrastructure.output.jpa.mapper.UserEntityMapper;
import dev.ferv.user_service.infrastructure.output.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistancePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    @Override
    public User getUserByEmail(String email) {
        return userEntityMapper.toUser(userRepository.findByEmail(email).orElse(null));
    }

    @Override
    public User getUser(Long userId) {
        return userEntityMapper.toUser(userRepository.findById(userId).orElse(null));  
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public List<User> getAllUser() {
        return userEntityMapper.toUserList(userRepository.findAll());
    }

}
