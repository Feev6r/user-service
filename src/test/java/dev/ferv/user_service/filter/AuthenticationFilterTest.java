package dev.ferv.user_service.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import dev.ferv.user_service.domain.model.Role;
import dev.ferv.user_service.domain.spi.IJwtPort;
import dev.ferv.user_service.infrastructure.output.jpa.adapter.security.JwtAdapter;
import dev.ferv.user_service.infrastructure.output.jpa.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

public class AuthenticationFilterTest {

    @InjectMocks
    private TestableJwtAuthenticationFilter jwtAuthentificationFilter;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock 
    IJwtPort jwtPort;


    @InjectMocks
    private JwtAdapter jwtAdapter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testFilter() throws ServletException, IOException {

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzM4NzE0MTIxLCJleHAiOjE3Mzg4MDA1MjF9.bArKRvBMbAdUAfozMcMTjoxAjImMabggM1gea7bRZvvk6DKSic38pO7yB4hyMeq3wPSL424L0WtQFtYWVCL1HQ";

        UserEntity userEntity = new UserEntity(); 
        userEntity.setId(1L);  
        userEntity.setEmail("fernandovillegass000@gmail.com");  
        userEntity.setRole(Role.OWNER);
        userEntity.setPassword("1234");

        
        when(jwtPort.extractUsername(token)).thenReturn("fernandovillegass000@gmail.com");
        when(jwtPort.isTokenValid(token, "fernandovillegass000@gmail.com")).thenReturn(true);
        when(userDetailsService.loadUserByUsername("fernandovillegass000@gmail.com")).thenReturn(userEntity);

        request.addHeader("Authorization", "Bearer " + token);


        jwtAuthentificationFilter.doFilterInternal(request, response, filterChain);

        // Verificar que el contexto de seguridad se actualizó
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
 
        //Verificar el metodo getUserIdBySecurityContext para obtener el igd del contexto
        String email = jwtAdapter.getUserEmailBySecurityContext();
        assertEquals("fernandovillegass000@gmail.com", email);

        // Verificar que el filtro continuó la cadena
        verify(filterChain, times(1)).doFilter(request, response);
    }

}
