package dev.ferv.user_service.infrastructure.configuration.Beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.ferv.user_service.domain.api.IJwtServicePort;
import dev.ferv.user_service.domain.spi.IJwtPort;
import dev.ferv.user_service.domain.usecase.JwtUseCase;
import dev.ferv.user_service.infrastructure.output.jpa.adapter.security.JwtAdapter;

@Configuration
public class JwtConfiguration {

    @Bean
    IJwtPort iJwtPort(){
        return new JwtAdapter();
    }

    @Bean
    IJwtServicePort iJwtServicePort(){
        return new JwtUseCase(iJwtPort());
    }

}
