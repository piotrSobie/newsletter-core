package com.example.newslettercore.application.rest.user.model;

import com.example.newslettercore.domain.user.model.Role;
import lombok.Data;

@Data
public class UserResponse {

    private String id;

    private String name;

    private String password;

    private String email;

    private Role role;
}