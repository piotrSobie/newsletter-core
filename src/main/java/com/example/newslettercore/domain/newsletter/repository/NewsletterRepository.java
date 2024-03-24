package com.example.newslettercore.domain.newsletter.repository;

import com.example.newslettercore.domain.newsletter.model.Newsletter;
import com.example.newslettercore.domain.newsletter.model.NewsletterTask;
import com.example.newslettercore.domain.newsletter.model.NewsletterTaskStatus;
import com.example.newslettercore.domain.newsletter.model.Template;

import java.util.Collection;
import java.util.Optional;

public interface NewsletterRepository {

    Newsletter save(Newsletter newsletter);

    Optional<Newsletter> findNewsletterById(String newsletterId);

    Collection<Newsletter> findNewslettersByParams(String tag);

    void deleteNewsletterById(String newsletterId);

    Optional<Template> findTemplateById(String templateId);

    Collection<Template> findTemplatesByParams(String newsletterId, String channel);

    void deleteTemplateById(String templateId);

    NewsletterTask saveNewsletterTask(NewsletterTask newsletterTask);

    Collection<NewsletterTask> findAllNewsletterTaskInStatusAndPastTriggerTime(NewsletterTaskStatus status);
}
