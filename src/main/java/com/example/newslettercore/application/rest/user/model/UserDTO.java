package com.example.newslettercore.application.rest.user.model;

import lombok.Data;

@Data
public class UserDTO {

    private String id;

    private String name;

    private String password;

    private String email;

    private String token;
}