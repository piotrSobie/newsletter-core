package com.example.newslettercore.application.adapter;

import com.example.newslettercore.domain.port.PasswordEncipher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncipher implements PasswordEncipher {

    private final BCryptPasswordEncoder passwordEncoder;

    public BCryptPasswordEncipher() {

        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encodePassword(String plainPassword) {

        return passwordEncoder.encode(plainPassword);
    }
}
