package com.example.newslettercore.application.rest.newsletter.mapper;

import com.example.newslettercore.application.rest.newsletter.model.NewsletterResponse;
import com.example.newslettercore.domain.newsletter.model.Newsletter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface RestNewsletterMapper {

    RestNewsletterMapper getMapper = Mappers.getMapper(RestNewsletterMapper.class);

    NewsletterResponse newsletterToNewsletterResponse(Newsletter newsletter);

    Collection<NewsletterResponse> newslettersToNewsletterResponses(Collection<Newsletter> newsletters);
}
