package com.example.newslettercore.application.rest.newsletter.model;

import lombok.Data;

import java.util.List;

@Data
public class TemplateResponse {

    private String id;

    private List<String> channels;

    private String text;

    private String newsletterId;
}
