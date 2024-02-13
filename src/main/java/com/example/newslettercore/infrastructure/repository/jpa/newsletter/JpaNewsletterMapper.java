package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import com.example.newslettercore.domain.newsletter.model.Newsletter;
import com.example.newslettercore.domain.newsletter.model.Template;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Mapper
public interface JpaNewsletterMapper {

    JpaNewsletterMapper getMapper = Mappers.getMapper(JpaNewsletterMapper.class);

    NewsletterEntity mapToNewsletterEntity(Newsletter newsletter);

    @AfterMapping
    default void afterMapToNewsletterEntity(Newsletter newsletter, @MappingTarget NewsletterEntity newsletterEntity) {

        if (CollectionUtils.isNotEmpty(newsletter.getTemplates())) {
            newsletterEntity.getTemplates() //
                    .forEach(templateEntity -> templateEntity.setNewsletter(newsletterEntity));
        }
    }

    default TemplateEntity templateToTemplateEntity(Template template) {

        if ( template == null ) {
            return null;
        }

        TemplateEntity templateEntity = new TemplateEntity();

        templateEntity.setId( template.getId() );
        List<String> list = template.getChannels();
        if ( list != null ) {
            templateEntity.setChannels( new ArrayList<>( list ) );
        }
        templateEntity.setText( template.getText() );

        return templateEntity;
    }

    default Collection<Newsletter> mapToNewsletters(Collection<NewsletterEntity> newsletterEntities) {

        return newsletterEntities.stream() //
                .map(getMapper::mapToNewsletter) //
                .collect(toList());
    }

    default Newsletter mapToNewsletter(NewsletterEntity newsletterEntity) {

        Newsletter newsletter = new Newsletter(newsletterEntity.getId(), newsletterEntity.getTags(), newsletterEntity.getCronSendingFrequency());
        newsletter.setTemplates(mapToTemplates(newsletterEntity.getTemplates()));

        afterMapToNewsletter(newsletterEntity, newsletter);

        return newsletter;
    }

    default Template mapToTemplate(TemplateEntity templateEntity) {

        return new Template(templateEntity.getId(), templateEntity.getChannels(), templateEntity.getText(), null);
    }

    default List<Template> mapToTemplates(Collection<TemplateEntity> templateEntities) {

        if (CollectionUtils.isEmpty(templateEntities)) {
            return new ArrayList<>();
        }

        return templateEntities.stream()
                .map(this::mapToTemplate)
                .collect(Collectors.toList());
    }

    @AfterMapping
    default void afterMapToNewsletter(NewsletterEntity newsletterEntity, @MappingTarget Newsletter newsletter) {

        if (CollectionUtils.isNotEmpty(newsletterEntity.getTemplates())) {
            newsletter.getTemplates() //
                    .forEach(template -> template.setNewsletter(newsletter));
        }
    }
}
