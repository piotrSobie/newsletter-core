package com.example.newslettercore.domain.newsletter.model;

import com.example.newslettercore.domain.exception.TemplateCanalsNotFoundException;
import com.example.newslettercore.domain.exception.TemplateTextInvalidException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

@Getter
public class Template {

    private String id;

    private List<String> channels;

    private String text;

    @Setter
    private Newsletter newsletter;

    public Template(String id, List<String> channels, String text, Newsletter newsletter) {

        this(channels, text, newsletter);
        this.id = id;
    }

    public Template(List<String> channels, String text, Newsletter newsletter) {

        validateTemplateData(channels, text);

        this.channels = channels;
        this.text = text;
        this.newsletter = newsletter;
    }

    private void validateTemplateData(List<String> canals, String text) {

        validateCanals(canals);
        validateText(text);
    }

    private void validateCanals(List<String> canals) {

        if (CollectionUtils.isEmpty(canals)) {
            throw new TemplateCanalsNotFoundException();
        }
    }

    private void validateText(String text) {

        if (Strings.isBlank(text)) {
            throw new TemplateTextInvalidException();
        }
    }

    public Template updateTemplate(List<String> canals, String text) {

        if (!CollectionUtils.isEmpty(canals)) {
            this.channels = canals;
        }

        if (Strings.isNotEmpty(text)) {
            this.text = text;
        }

        newsletter.getTemplates().removeIf(template -> template.getId().equals(id));
        newsletter.getTemplates().add(this);

        return this;
    }
}
