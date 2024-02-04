package com.example.newslettercore.infrastructure.repository.jpa.newsletter.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface SpringDataJpaTemplateRepository extends JpaRepository<TemplateEntity, String> {

    @Query(value = "select t from TemplateEntity t join t.canals c join t.newsletterEntity n where n.id = :newsletterId and c = :canal")
    Collection<TemplateEntity> findByParams(String newsletterId, String canal);
}
