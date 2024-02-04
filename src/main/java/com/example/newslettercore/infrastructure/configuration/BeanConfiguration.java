package com.example.newslettercore.infrastructure.configuration;

import com.example.newslettercore.NewsletterCoreApplication;
import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import com.example.newslettercore.domain.newsletter.service.DomainNewsletterService;
import com.example.newslettercore.domain.newsletter.service.NewsletterService;
import com.example.newslettercore.domain.newsletter.template.repository.TemplateRepository;
import com.example.newslettercore.domain.newsletter.template.service.DomainTemplateService;
import com.example.newslettercore.domain.newsletter.template.service.TemplateService;
import com.example.newslettercore.domain.user.repository.UserRepository;
import com.example.newslettercore.domain.user.service.DomainUserService;
import com.example.newslettercore.domain.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = NewsletterCoreApplication.class)
public class BeanConfiguration {

    @Bean
    UserService userService(UserRepository userRepository) {

        return new DomainUserService(userRepository);
    }

    @Bean
    NewsletterService newsletterService(NewsletterRepository newsletterRepository) {

        return new DomainNewsletterService(newsletterRepository);
    }

    @Bean
    TemplateService templateService(TemplateRepository templateRepository, NewsletterService newsletterService) {

        return new DomainTemplateService(templateRepository, newsletterService);
    }
}
