package com.example.newslettercore.application.rest.newsletter.mapper;

import com.example.newslettercore.application.rest.newsletter.model.TemplateResponse;
import com.example.newslettercore.domain.newsletter.model.Template;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface RestTemplateMapper {

    RestTemplateMapper getMapper = Mappers.getMapper(RestTemplateMapper.class);

    @Mapping(source = "newsletter.id", target = "newsletterId")
    TemplateResponse templateToTemplateResponse(Template template);

    Collection<TemplateResponse> templatesToTemplateResponses(Collection<Template> templates);
}
