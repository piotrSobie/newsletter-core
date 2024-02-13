package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface SpringDataJpaTemplateRepository extends JpaRepository<TemplateEntity, String> {

    @Query(value = "select t from TemplateEntity t join t.channels c join t.newsletter n where n.id = :newsletterId and c = :channel")
    Collection<TemplateEntity> findByParams(String newsletterId, String channel);
}
