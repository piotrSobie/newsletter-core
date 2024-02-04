package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SpringDataJpaNewsletterRepository extends JpaRepository<NewsletterEntity, String> {

    @Query(value = "select n from NewsletterEntity n join n.tags t where t = :tag")
    Collection<NewsletterEntity> findByParams(String tag);
}
