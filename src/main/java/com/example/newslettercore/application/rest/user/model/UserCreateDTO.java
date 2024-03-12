package com.example.newslettercore.application.rest.user.model;

import com.example.newslettercore.domain.user.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCreateDTO {

    @NotBlank(message = "User name can't be empty")
    private String name;

    @NotBlank(message = "User password can't be empty")
    private String password;

    @NotBlank(message = "User email can't be empty")
    private String email;

    @NotNull(message = "User role can't be empty")
    private Role role;
}
