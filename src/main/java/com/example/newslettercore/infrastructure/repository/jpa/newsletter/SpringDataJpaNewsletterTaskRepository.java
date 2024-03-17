package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import com.example.newslettercore.domain.newsletter.model.NewsletterTaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SpringDataJpaNewsletterTaskRepository extends JpaRepository<NewsletterTaskEntity, String> {

    Optional<NewsletterTaskEntity> findByNewsletterTaskStatusAndNextTriggerIsLessThanEqual(NewsletterTaskStatus newsletterTaskStatus, LocalDateTime timeBefore);

}
