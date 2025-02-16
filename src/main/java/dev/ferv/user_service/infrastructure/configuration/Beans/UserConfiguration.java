package dev.ferv.user_service.infrastructure.configuration.Beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import dev.ferv.user_service.domain.api.IUserServicePort;
import dev.ferv.user_service.domain.spi.IUserPersistancePort;
import dev.ferv.user_service.domain.usecase.UserUseCase;
import dev.ferv.user_service.infrastructure.output.jpa.adapter.UserAdapter;
import dev.ferv.user_service.infrastructure.output.jpa.mapper.UserEntityMapper;
import dev.ferv.user_service.infrastructure.output.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Bean
    IUserPersistancePort userPersistancePort(){
        return new UserAdapter(userRepository, userEntityMapper);
    }

    @Bean
    IUserServicePort iUserServicePort(){
        return new UserUseCase(userPersistancePort());
    }
 
}
