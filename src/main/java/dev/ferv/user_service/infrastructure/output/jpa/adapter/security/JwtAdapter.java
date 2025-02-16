package dev.ferv.user_service.infrastructure.output.jpa.adapter.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import dev.ferv.user_service.domain.model.User;
import dev.ferv.user_service.domain.spi.IJwtPort;
import dev.ferv.user_service.infrastructure.output.jpa.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class JwtAdapter implements IJwtPort{


    @Value("${my-app.security.jwt.expiration}")
    private Long jwtExpiration;

    @Value("${my-app.security.jwt.secret-key}")
    private String secretKey;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // :: para invocar a un metodo como expresion lambda en este caso
    }

    @Override
    public String getUserEmailBySecurityContext(){
        // contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();    

        // El principal es el id que almacenamos en el filtro
        return userEntity.getEmail();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

         Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(getSignInKey()) // verifica la signature
                .build()
                .parseSignedClaims(token); // analizamos los claims

        return claimsJws.getPayload(); // devolvermos los claims
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }


    @Override
    public String generateToken(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("roles", user.getRole().name()); // Incluye los roles en el token

        return generateToken(extraClaims, user.getUsername());
    }


    private String generateToken(Map<String, Object> extraClaims, String username) { //En el contexto de SpringSecurity username equivale a un valor unico, ej: el subject. En este caso seria el email.


        return Jwts
                .builder()
                .claims(extraClaims) // añadimos claims
                .subject(username) // el subject es algo unico del usuario
                .issuedAt(new Date()) // fecha de emision
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration)) // fecha de expiracion
                .signWith(getSignInKey())
                .compact();
        
    }

    @Override
    public boolean isTokenValid(String token, String username) {

        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
