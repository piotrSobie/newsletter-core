package com.example.newslettercore.application.rest.newsletter.template;

import com.example.newslettercore.application.rest.newsletter.NewsletterController;
import com.example.newslettercore.domain.newsletter.template.model.TemplateCreateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateQueryParams;
import com.example.newslettercore.domain.newsletter.template.model.TemplateUpdateDTO;
import com.example.newslettercore.domain.newsletter.template.service.TemplateService;
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
    public static final String NEWSLETTER_ID_ENdPOINT = "/{newsletterId}";

    private final TemplateService templateService;

    @Autowired
    public TemplateController(TemplateService templateService) {

        this.templateService = templateService;
    }

    @PostMapping(path = NEWSLETTER_ID_ENdPOINT + TEMPLATE_ENDPOINT)
    public ResponseEntity<TemplateDTO> createTemplate(@Nonnull @PathVariable("newsletterId") String newsletterId,
                                                      @RequestBody @Valid TemplateCreateDTO templateCreateDTO) {

        TemplateDTO templateDTO = templateService.createTemplate(newsletterId, templateCreateDTO);
        return new ResponseEntity<>(templateDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = NEWSLETTER_ID_ENdPOINT + TEMPLATE_ENDPOINT)
    public ResponseEntity<Collection<TemplateDTO>> findTemplatesByParams(@Nonnull @PathVariable("newsletterId") String newsletterId,
                                                                           TemplateQueryParams templateQueryParams) {

        Collection<TemplateDTO> templates = templateService.getByParams(newsletterId, templateQueryParams);
        return ResponseEntity.ok(templates);
    }

    @GetMapping(path = TEMPLATE_ENDPOINT + "/{templateId}")
    public ResponseEntity<TemplateDTO> findTemplateById(@Nonnull @PathVariable("templateId") String templateId) {

        TemplateDTO templateDTO = templateService.getById(templateId);
        return ResponseEntity.ok(templateDTO);
    }

    @PatchMapping(path = TEMPLATE_ENDPOINT + "/{templateId}")
    public ResponseEntity<TemplateDTO> updateTemplate(@Nonnull @PathVariable("templateId") String templateId,
                                                      @RequestBody @Valid TemplateUpdateDTO templateUpdateDTO) {

        TemplateDTO templateDTO = templateService.updateTemplate(templateId, templateUpdateDTO);
        return ResponseEntity.ok(templateDTO);
    }

    @DeleteMapping(path = TEMPLATE_ENDPOINT + "/{templateId}")
    public ResponseEntity<Void> deleteTemplate(@Nonnull @PathVariable("templateId") String templateId) {

        templateService.deleteTemplate(templateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
