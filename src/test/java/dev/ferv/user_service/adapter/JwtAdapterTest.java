package dev.ferv.user_service.adapter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import dev.ferv.user_service.UserServiceApplication;
import dev.ferv.user_service.domain.model.User;
import dev.ferv.user_service.domain.spi.IJwtPort;


@SpringBootTest(classes = UserServiceApplication.class)
@TestPropertySource(properties = {
    "my-app.security.jwt.secret-key=aa51197e3861ba9d8e7d3add5f2bc30862c891e0a3762935c8b8ecf6a61a9994",
    "my-app.security.jwt.expiration=86400000"

})
public class JwtAdapterTest {
    
    @Value("${my-app.security.jwt.expiration}")
    private Long jwtExpiration;


    @Value("${my-app.security.jwt.secret-key}")
    private String secretKey;

    @Autowired
    private IJwtPort jwtAdapter; 



    @Test
    public void testEnviornmentVariables(){


        assertNotNull(jwtExpiration);
        assertNotNull(secretKey);
        System.out.println(jwtExpiration);
        System.out.println(secretKey);
    }

    
    @Test
    public void testGenerateToken() {


        User user = new User();
        user.setId(1L);
        user.setEmail("fernandovillegass000@gmail.com");
        
        String token = jwtAdapter.generateToken(user);
        String email = jwtAdapter.extractUsername(token);

        assertNotNull(token);
        assertNotNull(email);

        System.out.println("Token: " + token);
        System.out.println("UserId: " + email);

    }


}
