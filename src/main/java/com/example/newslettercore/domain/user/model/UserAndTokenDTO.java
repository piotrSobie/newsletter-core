package com.example.newslettercore.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAndTokenDTO {

    private UserDTO userDTO;

    private String token;
}
