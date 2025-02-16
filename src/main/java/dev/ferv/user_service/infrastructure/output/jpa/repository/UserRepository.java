package dev.ferv.user_service.infrastructure.output.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ferv.user_service.infrastructure.output.jpa.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>  {

    // Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByEmail(String email);
}
