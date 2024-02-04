package com.example.newslettercore.infrastructure.repository.jpa.newsletter.template;

import com.example.newslettercore.domain.newsletter.template.model.TemplateCreateDTO;
import com.example.newslettercore.domain.newsletter.template.model.TemplateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface TemplateMapper {

    TemplateMapper getMapper = Mappers.getMapper(TemplateMapper.class);

    TemplateEntity mapToTemplateEntity(TemplateCreateDTO templateCreateDTO);

    TemplateEntity mapToTemplateEntity(TemplateDTO templateDTO);

    TemplateDTO mapToTemplateDTO(TemplateEntity templateEntity);

    Collection<TemplateDTO> mapToTemplateDTO(Collection<TemplateEntity> templateEntities);
}
