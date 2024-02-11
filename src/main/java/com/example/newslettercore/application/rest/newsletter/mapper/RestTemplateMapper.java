package com.example.newslettercore.application.rest.newsletter.mapper;

import com.example.newslettercore.application.rest.newsletter.model.TemplateDTO;
import com.example.newslettercore.domain.newsletter.model.Template;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface RestTemplateMapper {

    RestTemplateMapper getMapper = Mappers.getMapper(RestTemplateMapper.class);

    TemplateDTO mapToTemplateDTO(Template template);

    Collection<TemplateDTO> mapToTemplateDTOs(Collection<Template> templates);
}
