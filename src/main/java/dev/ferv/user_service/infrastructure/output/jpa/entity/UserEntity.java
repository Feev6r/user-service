package dev.ferv.user_service.infrastructure.output.jpa.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.ferv.user_service.domain.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) 
    private Role role;

    private String firstname;
    private String lastname;
    private String identityNumber;
    private String phoneNumber;
    private LocalDate birthdate;
    private String email;
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // se puede devolver cualquier coleccion que tenga
                                                                     // un tipo de dato que extends GrantedAuthority
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.getRole())); // retorna una lista de SimpleGrantedAuthority
    }

    @Override
    public String getUsername() {
        return this.id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
