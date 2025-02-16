package dev.ferv.user_service.filter;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetailsService;

import dev.ferv.user_service.domain.spi.IJwtPort;
import dev.ferv.user_service.infrastructure.output.jpa.adapter.security.JwtAuthentificationFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestableJwtAuthenticationFilter extends JwtAuthentificationFilter{

   public TestableJwtAuthenticationFilter(IJwtPort jwtService, UserDetailsService userDetailsService) {
           super(jwtService, userDetailsService);
       }
   
   @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        super.doFilterInternal(request, response, filterChain);
    }

}
