package com.example.newslettercore.domain.user.repository;

import com.example.newslettercore.domain.user.model.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(String userId);

    Optional<User> findByEmail(String email);
}
