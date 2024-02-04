package com.example.newslettercore.domain.newsletter.model;

import lombok.Data;

import java.util.List;

@Data
public class NewsletterUpdateDTO {

    private List<String> tags;

    private String cronSendingFrequency;
}
