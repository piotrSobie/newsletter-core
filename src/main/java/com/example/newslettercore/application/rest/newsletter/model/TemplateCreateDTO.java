package com.example.newslettercore.application.rest.newsletter.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class TemplateCreateDTO {

    @NotEmpty(message = "At least one canal must be specified.")
    private List<String> canals;

    @NotBlank(message = "Template text can't be null.")
    private String text;
}
