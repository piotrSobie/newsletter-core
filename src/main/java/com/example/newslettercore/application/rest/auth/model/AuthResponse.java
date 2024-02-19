package com.example.newslettercore.application.rest.auth.model;

import com.example.newslettercore.application.rest.user.model.UserResponse;
import lombok.Data;

@Data
public class AuthResponse {

    private final UserResponse userResponse;

    private final String token;
}
