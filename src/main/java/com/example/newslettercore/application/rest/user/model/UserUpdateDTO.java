package com.example.newslettercore.application.rest.user.model;

import lombok.Data;

@Data
public class UserUpdateDTO {

    private String name;

    private String password;

    private String email;
}
