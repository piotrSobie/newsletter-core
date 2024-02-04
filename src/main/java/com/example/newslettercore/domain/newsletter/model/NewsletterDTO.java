package com.example.newslettercore.domain.newsletter.model;

import lombok.Data;

import java.util.List;

@Data
public class NewsletterDTO {

    private String id;

    private List<String> tags;

    private String cronSendingFrequency;
}
