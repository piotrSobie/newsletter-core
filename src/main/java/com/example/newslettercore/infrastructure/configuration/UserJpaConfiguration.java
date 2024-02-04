package com.example.newslettercore.infrastructure.configuration;

import com.example.newslettercore.infrastructure.repository.jpa.user.SpringDataJpaUserRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataJpaUserRepository.class)
public class UserJpaConfiguration {
}
