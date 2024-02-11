package com.example.newslettercore.application.rest.newsletter.model;

import lombok.Data;

import java.util.List;

@Data
public class NewsletterDTO {

    private String id;

    private List<String> tags;

    private String cronSendingFrequency;

    private List<NewslettersTemplateDTO> templates;
}
