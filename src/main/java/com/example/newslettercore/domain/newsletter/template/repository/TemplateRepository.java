package com.example.newslettercore.domain.newsletter.template.repository;

import com.example.newslettercore.domain.newsletter.model.NewsletterDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateCreateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateQueryParams;

import java.util.Collection;
import java.util.Optional;

public interface TemplateRepository {

    TemplateDTO save(TemplateCreateDTO templateCreateDTO, NewsletterDTO newsletterDTO);

    TemplateDTO update(TemplateDTO templateDTO);

    Collection<TemplateDTO> getByParams(String newsletterId, TemplateQueryParams templateQueryParams);

    Optional<TemplateDTO> findById(String templateId);

    void deleteById(String templateId);
}
