package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import com.example.newslettercore.domain.newsletter.model.NewsletterTask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Mapper
public interface JpaNewsletterTaskMapper {

    JpaNewsletterTaskMapper getMapper = Mappers.getMapper(JpaNewsletterTaskMapper.class);

    NewsletterTaskEntity newsletterTaskToNewsletterTaskEntity(NewsletterTask newsletterTask);

    default NewsletterTask newsletterTaskEntityToNewsletterTask(NewsletterTaskEntity newsletterTaskEntity) {

        return new NewsletterTask(newsletterTaskEntity.getId(), newsletterTaskEntity.getNewsletterId(), newsletterTaskEntity.getNextTrigger(),
                newsletterTaskEntity.getNewsletterTaskStatus(), newsletterTaskEntity.getVersion());
    }

    default Collection<NewsletterTask> newsletterTaskEntitiesToNewsletterTasks(Collection<NewsletterTaskEntity> newsletterTaskEntities) {

        return newsletterTaskEntities.stream() //
                .map(this::newsletterTaskEntityToNewsletterTask) //
                .collect(toList());
    }
}
