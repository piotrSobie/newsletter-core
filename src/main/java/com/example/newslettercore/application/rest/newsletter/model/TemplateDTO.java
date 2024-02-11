package com.example.newslettercore.application.rest.newsletter.model;

import lombok.Data;

import java.util.List;

@Data
public class TemplateDTO {

    private String id;

    private List<String> canals;

    private String text;

    private TemplatesNewsletterDTO newsletter;
}
