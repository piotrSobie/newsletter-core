package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import com.example.newslettercore.domain.newsletter.model.NewsletterCreteDTO;
import com.example.newslettercore.domain.newsletter.model.NewsletterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface NewsletterMapper {

    NewsletterMapper getMapper = Mappers.getMapper(NewsletterMapper.class);

    NewsletterEntity mapToNewsletterEntity(NewsletterCreteDTO newsletterCreteDTO);

    NewsletterEntity mapToNewsletterEntity(NewsletterDTO newsletterDTO);

    NewsletterDTO mapToNewsletterDTO(NewsletterEntity newsletterEntity);

    Collection<NewsletterDTO> mapToNewsletterDTO(Collection<NewsletterEntity> newsletterEntities);
}
