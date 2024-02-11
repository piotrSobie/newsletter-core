package com.example.newslettercore.application.rest.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateDTO {

    @NotBlank(message = "User name can't be empty")
    private String name;

    @NotBlank(message = "User password can't be empty")
    private String password;

    @NotBlank(message = "User email can't be empty")
    private String email;
}
