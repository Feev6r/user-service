package dev.ferv.user_service.infrastructure.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.ferv.user_service.domain.model.Role;
import dev.ferv.user_service.infrastructure.output.jpa.adapter.security.JwtAuthentificationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthentificationFilter jwtAuthentificationFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
            .disable()
            )
            .authorizeHttpRequests(authorize -> authorize
                // .requestMatchers("/auth/registerAdmin").permitAll() //no esta es necesario estar autenticado porque es para testeo
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                
                .requestMatchers("/auth/registerClient").permitAll()
                .requestMatchers("/auth/authenticate").permitAll()
                .requestMatchers("/user/getAll").hasRole(Role.ADMIN.name())
                .requestMatchers("/auth/registerOwner").hasRole(Role.ADMIN.name())
                .requestMatchers("/auth/registerEmployee").hasRole(Role.OWNER.name())
                .requestMatchers("/user/get/{id}").hasRole(Role.OWNER.name())
                .requestMatchers("/user/getContact/{id}").hasRole(Role.EMPLOYEE.name())
                .anyRequest().authenticated()
            )
            .logout(logout -> logout
            .permitAll()
            )
            .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)   
            )
            .authenticationProvider(authenticationProvider
            ).addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

}
