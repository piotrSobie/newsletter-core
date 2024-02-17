package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import com.example.newslettercore.domain.newsletter.model.Newsletter;
import com.example.newslettercore.domain.newsletter.model.Template;
import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class JpaNewsletterRepository implements NewsletterRepository {

    private final SpringDataJpaNewsletterRepository newsletterRepository;
    private final SpringDataJpaTemplateRepository templateRepository;

    @Autowired
    public JpaNewsletterRepository(SpringDataJpaNewsletterRepository newsletterRepository, SpringDataJpaTemplateRepository templateRepository) {

        this.newsletterRepository = newsletterRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public Newsletter save(Newsletter newsletter) {

        NewsletterEntity newsletterEntity = JpaNewsletterMapper.getMapper.newsletterToNewsletterEntity(newsletter);
        NewsletterEntity savedNewsletter = newsletterRepository.saveAndFlush(newsletterEntity);
        return JpaNewsletterMapper.getMapper.newsletterEntityToNewsletter(savedNewsletter);
    }

    @Override
    public Optional<Newsletter> findNewsletterById(String newsletterId) {

        Optional<NewsletterEntity> newsletterOptional = newsletterRepository.findById(newsletterId);
        return newsletterOptional.map(JpaNewsletterMapper.getMapper::newsletterEntityToNewsletter);
    }

    @Override
    public Collection<Newsletter> findNewslettersByParams(String tag) {

        Collection<NewsletterEntity> newsletterEntities = newsletterRepository.findNewslettersByParams(tag);
        return JpaNewsletterMapper.getMapper.newsletterEntitiesToNewsletters(newsletterEntities);
    }

    @Override
    public void deleteNewsletterById(String newsletterId) {

        newsletterRepository.deleteById(newsletterId);
    }

    @Override
    public Optional<Template> findTemplateById(String templateId) {

        Optional<TemplateEntity> templateEntityOptional = templateRepository.findById(templateId);
        return templateEntityOptional.map(templateEntity -> {
            Template template = JpaNewsletterMapper.getMapper.templateEntityToTemplate(templateEntityOptional.get());
            Newsletter newsletter = JpaNewsletterMapper.getMapper.newsletterEntityToNewsletter(templateEntityOptional.get().getNewsletter());
            template.setNewsletter(newsletter);
            return template;
        });
    }

    @Override
    public Collection<Template> findTemplatesByParams(String newsletterId, String channel) {

        Collection<TemplateEntity> templateEntities = templateRepository.findByParams(newsletterId, channel);
        return JpaNewsletterMapper.getMapper.templateEntitiesToTemplates(templateEntities);
    }

    @Override
    public void deleteTemplateById(String templateId) {

        templateRepository.deleteById(templateId);
    }
}
