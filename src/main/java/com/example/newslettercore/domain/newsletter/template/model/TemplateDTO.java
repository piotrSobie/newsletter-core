package com.example.newslettercore.domain.newsletter.template.model;

import lombok.Data;

import java.util.List;

@Data
public class TemplateDTO {

    private String id;

    private List<String> canals;

    private String text;
}
