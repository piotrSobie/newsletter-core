package com.example.newslettercore.domain.newsletter.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class NewsletterCreteDTO {

    @NotEmpty(message = "At least one tag must be specified.")
    private Set<String> tags;

    @NotBlank(message = "Sending frequency can't be blank.")
    private String cronSendingFrequency;
}
