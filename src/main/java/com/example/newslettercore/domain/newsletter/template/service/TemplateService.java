package com.example.newslettercore.domain.newsletter.template.service;

import com.example.newslettercore.domain.newsletter.template.model.TemplateCreateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateQueryParams;
import com.example.newslettercore.domain.newsletter.template.model.TemplateUpdateDTO;

import java.util.Collection;

public interface TemplateService {

    TemplateDTO createTemplate(String newsletterId, TemplateCreateDTO templateCreateDTO);

    Collection<TemplateDTO> getByParams(String newsletterId, TemplateQueryParams templateQueryParams);

    TemplateDTO getById(String templateId);

    TemplateDTO updateTemplate(String templateId, TemplateUpdateDTO templateUpdateDTO);

    void deleteTemplate(String templateId);
}
