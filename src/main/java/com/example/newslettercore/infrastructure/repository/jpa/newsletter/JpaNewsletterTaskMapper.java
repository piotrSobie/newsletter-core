package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import com.example.newslettercore.domain.newsletter.model.NewsletterTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JpaNewsletterTaskMapper {

    JpaNewsletterTaskMapper getMapper = Mappers.getMapper(JpaNewsletterTaskMapper.class);

    NewsletterTaskEntity newsletterTaskToNewsletterTaskEntity(NewsletterTask newsletterTask);

    default NewsletterTask newsletterTaskEntityToNewsletterTask(NewsletterTaskEntity newsletterTaskEntity) {

        return new NewsletterTask(newsletterTaskEntity.getId(), newsletterTaskEntity.getNewsletterId(), newsletterTaskEntity.getNextTrigger(),
                newsletterTaskEntity.getNewsletterTaskStatus(), newsletterTaskEntity.getVersion());
    }
}
