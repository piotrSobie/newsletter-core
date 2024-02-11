package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import com.example.newslettercore.application.rest.newsletter.model.NewsletterQueryParams;
import com.example.newslettercore.domain.newsletter.model.Newsletter;
import com.example.newslettercore.domain.newsletter.model.Template;
import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class JpaDbNewsletterRepository implements NewsletterRepository {

    private final SpringDataJpaNewsletterRepository newsletterRepository;
    private final SpringDataJpaTemplateRepository templateRepository;

    @Autowired
    public JpaDbNewsletterRepository(SpringDataJpaNewsletterRepository newsletterRepository, SpringDataJpaTemplateRepository templateRepository) {

        this.newsletterRepository = newsletterRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public Newsletter save(Newsletter newsletter) {

        NewsletterEntity newsletterEntity = JpaDbNewsletterMapper.getMapper.mapToNewsletterEntity(newsletter);
        NewsletterEntity savedNewsletter = newsletterRepository.saveAndFlush(newsletterEntity);
        return JpaDbNewsletterMapper.getMapper.mapToNewsletter(savedNewsletter);
    }

    @Override
    public Optional<Newsletter> findNewsletterById(String newsletterId) {

        Optional<NewsletterEntity> newsletterOptional = newsletterRepository.findById(newsletterId);

        if (newsletterOptional.isPresent()) {
            Newsletter newsletter = JpaDbNewsletterMapper.getMapper.mapToNewsletter(newsletterOptional.get());
            return Optional.of(newsletter);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Newsletter> findNewslettersByParams(NewsletterQueryParams newsletterQueryParams) {

        Collection<NewsletterEntity> newsletterEntities = newsletterRepository.findNewslettersByParams(newsletterQueryParams.getTag());
        return JpaDbNewsletterMapper.getMapper.mapToNewsletters(newsletterEntities);
    }

    @Override
    public void deleteNewsletterById(String newsletterId) {

        newsletterRepository.deleteById(newsletterId);
    }

    @Override
    public Optional<Template> findTemplateById(String templateId) {

        Optional<TemplateEntity> templateEntityOptional = templateRepository.findById(templateId);

        if (templateEntityOptional.isPresent()) {

            Template template = JpaDbNewsletterMapper.getMapper.mapToTemplate(templateEntityOptional.get());
            Newsletter newsletter = JpaDbNewsletterMapper.getMapper.mapToNewsletter(templateEntityOptional.get().getNewsletter());
            template.setNewsletter(newsletter);
            return Optional.of(template);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteTemplateById(String templateId) {

        templateRepository.deleteById(templateId);
    }
}
