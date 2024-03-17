package com.example.newslettercore.domain.newsletter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsletterTaskData {

    private String newsletterId;

    private List<String> emails;
}
