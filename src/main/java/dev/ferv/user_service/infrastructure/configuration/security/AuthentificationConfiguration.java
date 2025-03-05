package dev.ferv.user_service.infrastructure.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.ferv.user_service.infrastructure.output.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthentificationConfiguration {

    private final UserRepository userRepository;

    @Bean
    UserDetailsService userDetailService() {
    return username -> {
        try {
            Long userId = Long.parseLong(username);
            return userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid user id format");
        }
    };
    }
    
    @Bean
    AuthenticationProvider authenticationProvider(){ //fetch the user details and hash the password

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); //data access object = dao
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }


    @Bean 
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
