package com.example.newslettercore.infrastructure.configuration;

import com.example.newslettercore.infrastructure.repository.jpa.newsletter.template.SpringDataJpaTemplateRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataJpaTemplateRepository.class)
public class TemplateJpaConfiguration {
}
