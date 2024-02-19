package com.example.newslettercore.application.rest.user.model;

import com.example.newslettercore.domain.user.model.Role;
import lombok.Data;

@Data
public class UserResponse {

    protected String id;

    protected String name;

    protected String password;

    protected String email;

    protected Role role;
}