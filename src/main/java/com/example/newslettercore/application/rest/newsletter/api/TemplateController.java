package com.example.newslettercore.application.rest.newsletter.api;

import com.example.newslettercore.application.rest.newsletter.mapper.RestTemplateMapper;
import com.example.newslettercore.application.rest.newsletter.model.TemplateCreateDTO;
import com.example.newslettercore.application.rest.newsletter.model.TemplateDTO;
import com.example.newslettercore.application.rest.newsletter.model.TemplateQueryParams;
import com.example.newslettercore.application.rest.newsletter.model.TemplateUpdateDTO;
import com.example.newslettercore.domain.newsletter.model.Template;
import com.example.newslettercore.domain.newsletter.service.NewsletterService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = TemplateController.CONTROLLER_ENDPOINT + NewsletterController.NEWSLETTER_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class TemplateController {

    public static final String CONTROLLER_ENDPOINT = "/api/v1";
    public static final String TEMPLATE_ENDPOINT = "/template";
    public static final String NEWSLETTER_ID_ENDPOINT = "/{newsletterId}";

    private final NewsletterService newsletterService;

    @Autowired
    public TemplateController(NewsletterService newsletterService) {

        this.newsletterService = newsletterService;
    }

    @PostMapping(path = NEWSLETTER_ID_ENDPOINT + TEMPLATE_ENDPOINT)
    public ResponseEntity<TemplateDTO> createTemplate(@Nonnull @PathVariable("newsletterId") String newsletterId,
                                                      @RequestBody @Valid TemplateCreateDTO templateCreateDTO) {

        Template template = newsletterService.createTemplate(newsletterId, templateCreateDTO.getCanals(), templateCreateDTO.getText());
        TemplateDTO responseTemplateDTO = RestTemplateMapper.getMapper.mapToTemplateDTO(template);
        return new ResponseEntity<>(responseTemplateDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = NEWSLETTER_ID_ENDPOINT + TEMPLATE_ENDPOINT)
    public ResponseEntity<Collection<TemplateDTO>> findTemplatesByParams(@Nonnull @PathVariable("newsletterId") String newsletterId,
                                                                           TemplateQueryParams templateQueryParams) {

        Collection<Template> templates = newsletterService.getTemplatesByParams(newsletterId, templateQueryParams);
        Collection<TemplateDTO> responseTemplateDTOs = RestTemplateMapper.getMapper.mapToTemplateDTOs(templates);
        return ResponseEntity.ok(responseTemplateDTOs);
    }

    @GetMapping(path = TEMPLATE_ENDPOINT + "/{templateId}")
    public ResponseEntity<TemplateDTO> findTemplateById(@Nonnull @PathVariable("templateId") String templateId) {

        Template template = newsletterService.getTemplateById(templateId);
        TemplateDTO responseTemplateDTO = RestTemplateMapper.getMapper.mapToTemplateDTO(template);
        return ResponseEntity.ok(responseTemplateDTO);
    }

    @PatchMapping(path = TEMPLATE_ENDPOINT + "/{templateId}")
    public ResponseEntity<TemplateDTO> updateTemplate(@Nonnull @PathVariable("templateId") String templateId,
                                                      @RequestBody @Valid TemplateUpdateDTO templateUpdateDTO) {

        Template template = newsletterService.updateTemplate(templateId, templateUpdateDTO.getCanals(), templateUpdateDTO.getText());
        TemplateDTO responseTemplateDTO = RestTemplateMapper.getMapper.mapToTemplateDTO(template);
        return ResponseEntity.ok(responseTemplateDTO);
    }

    @DeleteMapping(path = TEMPLATE_ENDPOINT + "/{templateId}")
    public ResponseEntity<Void> deleteTemplate(@Nonnull @PathVariable("templateId") String templateId) {

        newsletterService.deleteTemplate(templateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
