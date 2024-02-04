package com.example.newslettercore.domain.user.repository;

import com.example.newslettercore.domain.user.model.UserCreateDTO;
import com.example.newslettercore.domain.user.model.UserDTO;

import java.util.Optional;

public interface UserRepository {

    UserDTO save(UserCreateDTO userCreateDTO);

    UserDTO save(UserDTO userDTO);

    Optional<UserDTO> findById(String userId);

    Optional<UserDTO> findByEmail(String email);
}
