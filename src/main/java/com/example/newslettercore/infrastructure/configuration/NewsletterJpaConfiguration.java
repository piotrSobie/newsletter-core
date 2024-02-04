package com.example.newslettercore.infrastructure.configuration;

import com.example.newslettercore.infrastructure.repository.jpa.newsletter.SpringDataJpaNewsletterRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataJpaNewsletterRepository.class)
public class NewsletterJpaConfiguration {
}
