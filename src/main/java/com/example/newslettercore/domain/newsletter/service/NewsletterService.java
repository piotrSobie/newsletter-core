package com.example.newslettercore.domain.newsletter.service;

import com.example.newslettercore.domain.exception.NewsletterCoreObjectNotFoundException;
import com.example.newslettercore.domain.exception.TemplateNotSavedException;
import com.example.newslettercore.domain.newsletter.model.Newsletter;
import com.example.newslettercore.domain.newsletter.model.Template;
import com.example.newslettercore.domain.newsletter.repository.NewsletterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public NewsletterService(NewsletterRepository newsletterRepository) {

        this.newsletterRepository = newsletterRepository;
    }

    public Newsletter createNewsletter(Set<String> tags, String cronSendingFrequency) {

        Newsletter newsletterToCreate = new Newsletter(tags, cronSendingFrequency);
        return newsletterRepository.save(newsletterToCreate);
    }

    public Newsletter getNewsletterById(String newsletterId) {

        return newsletterRepository.findNewsletterById(newsletterId)
                .orElseThrow(() -> new NewsletterCoreObjectNotFoundException(Newsletter.class.getSimpleName(), newsletterId));
    }

    public Collection<Newsletter> getNewslettersByParams(String tag) {

        return newsletterRepository.findNewslettersByParams(tag);
    }

    public Newsletter updateNewsletter(String newsletterId, Collection<String> tags, String cronSendingFrequency) {

        Newsletter foundNewsletter = getNewsletterById(newsletterId);
        Newsletter updatedNewsletter = foundNewsletter.updateNewsletter(tags, cronSendingFrequency);
        return newsletterRepository.save(updatedNewsletter);
    }

    public void deleteNewsletter(String newsletterId) {

        newsletterRepository.deleteNewsletterById(newsletterId);
    }

    public Template createTemplate(String newsletterId, List<String> canals, String text) {

        Newsletter newsletter = getNewsletterById(newsletterId);
        Template template = new Template(canals, text, newsletter);
        newsletter.addTemplate(template);
        Newsletter savedNewsletter = newsletterRepository.save(newsletter);

        Set<Template> newTemplates = savedNewsletter.getNewTemplates(newsletter.getTemplates());
        return newTemplates.stream().findFirst()
                .orElseThrow(TemplateNotSavedException::new);
    }

    public Collection<Template> getTemplatesByParams(String newsletterId, String channel) {

        return newsletterRepository.findTemplatesByParams(newsletterId, channel);
    }

    public Template getTemplateById(String templateId) {

        return newsletterRepository.findTemplateById(templateId)
                .orElseThrow(() -> new NewsletterCoreObjectNotFoundException(Template.class.getSimpleName(), templateId));
    }

    public Template updateTemplate(String templateId, List<String> canals, String text) {

        Template foundTemplate = getTemplateById(templateId);
        Template updatedTemplate = foundTemplate.updateTemplate(canals, text);

        Newsletter newsletter = updatedTemplate.getNewsletter();
        newsletterRepository.save(newsletter);
        return updatedTemplate;
    }

    public void deleteTemplate(String templateId) {

        newsletterRepository.deleteTemplateById(templateId);
    }
}
