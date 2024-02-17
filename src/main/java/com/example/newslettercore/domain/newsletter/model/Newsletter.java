package com.example.newslettercore.domain.newsletter.model;

import com.example.newslettercore.domain.exception.NewsletterCronSendingFrequencyInvalidException;
import com.example.newslettercore.domain.exception.NewsletterTagsInvalidException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Newsletter {

    private String id;

    private Collection<String> tags;

    private String cronSendingFrequency;

    @Setter
    private List<Template> templates;

    public Newsletter(String id, Collection<String> tags, String cronSendingFrequency, Template template) {

        this(id, tags, cronSendingFrequency);
        addTemplate(template);
    }

    public Newsletter(String id, Collection<String> tags, String cronSendingFrequency) {

        this(tags, cronSendingFrequency);
        this.id = id;
    }

    public Newsletter(Collection<String> tags, String cronSendingFrequency) {

        validateNewsletterData(tags, cronSendingFrequency);

        this.tags = tags;
        this.cronSendingFrequency = cronSendingFrequency;
    }

    private void validateNewsletterData(Collection<String> tags, String cronSendingFrequency) {

        validateTags(tags);
        validateCronSendingFrequency(cronSendingFrequency);
    }

    private void validateTags(Collection<String> tags) {

        if (CollectionUtils.isEmpty(tags)) {
            throw new NewsletterTagsInvalidException();
        }
    }

    private void validateCronSendingFrequency(String cronSendingFrequency) {

        if (Strings.isBlank(cronSendingFrequency)) {
            throw new NewsletterCronSendingFrequencyInvalidException();
        }
    }

    public Newsletter updateNewsletter(Collection<String> tags, String cronSendingFrequency) {

        if (!CollectionUtils.isEmpty(tags)) {
            this.tags = tags;
        }

        if (Strings.isNotBlank(cronSendingFrequency)) {
            this.cronSendingFrequency = cronSendingFrequency;
        }

        return this;
    }

    public Newsletter addTemplate(Template template) {

        if (CollectionUtils.isEmpty(templates)) {
            templates = new ArrayList<>();
        }

        templates.add(template);

        return this;
    }

    public Set<Template> getNewTemplates(List<Template> oldTemplates) {

        return templates.stream()
                .filter(template -> !oldTemplates.contains(template))
                .collect(Collectors.toSet());
    }
}
