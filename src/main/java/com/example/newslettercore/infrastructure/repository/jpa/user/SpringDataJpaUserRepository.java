package com.example.newslettercore.infrastructure.repository.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataJpaUserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
}