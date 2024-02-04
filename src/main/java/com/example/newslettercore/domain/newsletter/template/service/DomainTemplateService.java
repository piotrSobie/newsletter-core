package com.example.newslettercore.domain.newsletter.template.service;

import com.example.newslettercore.domain.exception.NewsletterCoreObjectNotFoundException;
import com.example.newslettercore.domain.newsletter.model.NewsletterDTO;
import com.example.newslettercore.domain.newsletter.service.NewsletterService;
import com.example.newslettercore.domain.newsletter.template.model.TemplateCreateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateQueryParams;
import com.example.newslettercore.domain.newsletter.template.model.TemplateUpdateDTO;
import com.example.newslettercore.domain.newsletter.template.repository.TemplateRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Collection;
import java.util.Optional;

public class DomainTemplateService implements TemplateService {

    private final TemplateRepository templateRepository;
    private final NewsletterService newsletterService;

    public DomainTemplateService(TemplateRepository templateRepository, NewsletterService newsletterService) {

        this.templateRepository = templateRepository;
        this.newsletterService = newsletterService;
    }

    @Override
    public TemplateDTO createTemplate(String newsletterId, TemplateCreateDTO templateCreateDTO) {

        NewsletterDTO newsletterDTO = newsletterService.getById(newsletterId);
        return templateRepository.save(templateCreateDTO, newsletterDTO);
    }

    @Override
    public Collection<TemplateDTO> getByParams(String newsletterId, TemplateQueryParams templateQueryParams) {

        return templateRepository.getByParams(newsletterId, templateQueryParams);
    }

    @Override
    public TemplateDTO getById(String templateId) {

        Optional<TemplateDTO> templateOptional = templateRepository.findById(templateId);

        if (templateOptional.isEmpty()) {
            throw new NewsletterCoreObjectNotFoundException(TemplateDTO.class.getSimpleName(), templateId);
        }

        return templateOptional.get();
    }

    @Override
    public TemplateDTO updateTemplate(String templateId, TemplateUpdateDTO templateUpdateDTO) {

        TemplateDTO templateDTO = getById(templateId);
        TemplateDTO updatedTemplateBeforeSave = updateTemplateData(templateDTO, templateUpdateDTO);
        return templateRepository.update(updatedTemplateBeforeSave);
    }

    @Override
    public void deleteTemplate(String templateId) {

        templateRepository.deleteById(templateId);
    }

    private TemplateDTO updateTemplateData(TemplateDTO templateDTO, TemplateUpdateDTO templateUpdateDTO) {

        if (!CollectionUtils.isEmpty(templateUpdateDTO.getCanals())) {
            templateDTO.setCanals(templateUpdateDTO.getCanals());
        }

        if (Strings.isNotBlank(templateUpdateDTO.getText())) {
            templateDTO.setText(templateUpdateDTO.getText());
        }

        return templateDTO;
    }
}
