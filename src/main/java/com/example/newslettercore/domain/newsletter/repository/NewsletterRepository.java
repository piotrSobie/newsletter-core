package com.example.newslettercore.domain.newsletter.repository;

import com.example.newslettercore.application.rest.newsletter.model.NewsletterQueryParams;
import com.example.newslettercore.domain.newsletter.model.Newsletter;
import com.example.newslettercore.domain.newsletter.model.Template;

import java.util.Collection;
import java.util.Optional;

public interface NewsletterRepository {

    Newsletter save(Newsletter newsletter);

    Optional<Newsletter> findNewsletterById(String newsletterId);

    Collection<Newsletter> findNewslettersByParams(NewsletterQueryParams newsletterQueryParams);

    void deleteNewsletterById(String newsletterId);

    Optional<Template> findTemplateById(String templateId);

    void deleteTemplateById(String templateId);
}
