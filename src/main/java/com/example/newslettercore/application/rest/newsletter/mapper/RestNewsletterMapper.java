package com.example.newslettercore.application.rest.newsletter.mapper;

import com.example.newslettercore.application.rest.newsletter.model.NewsletterDTO;
import com.example.newslettercore.domain.newsletter.model.Newsletter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface RestNewsletterMapper {

    RestNewsletterMapper getMapper = Mappers.getMapper(RestNewsletterMapper.class);

    NewsletterDTO mapToNewsletterDTO(Newsletter newsletter);

    Collection<NewsletterDTO> mapToNewsletterDTO(Collection<Newsletter> newsletters);
}
