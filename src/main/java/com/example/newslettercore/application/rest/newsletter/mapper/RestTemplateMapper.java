package com.example.newslettercore.application.rest.newsletter.mapper;

import com.example.newslettercore.application.rest.newsletter.model.TemplateResponse;
import com.example.newslettercore.domain.newsletter.model.Template;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface RestTemplateMapper {

    RestTemplateMapper getMapper = Mappers.getMapper(RestTemplateMapper.class);

    TemplateResponse mapToTemplateDTO(Template template);

    Collection<TemplateResponse> mapToTemplateDTOs(Collection<Template> templates);
}
