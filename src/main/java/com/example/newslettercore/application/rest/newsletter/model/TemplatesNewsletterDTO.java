package com.example.newslettercore.application.rest.newsletter.model;

import lombok.Data;

import java.util.List;

@Data
public class TemplatesNewsletterDTO {

    private final String id;

    private final List<String> tags;

    private final String cronSendingFrequency;
}