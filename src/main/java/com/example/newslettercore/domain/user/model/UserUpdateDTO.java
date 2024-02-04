package com.example.newslettercore.domain.user.model;

import lombok.Data;

@Data
public class UserUpdateDTO {

    private String name;

    private String password;

    private String email;
}
