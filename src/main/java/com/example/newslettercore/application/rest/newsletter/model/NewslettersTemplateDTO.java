package com.example.newslettercore.application.rest.newsletter.model;

import lombok.Data;

import java.util.List;

@Data
public class NewslettersTemplateDTO {

    private String id;

    private List<String> canals;

    private String text;
}
