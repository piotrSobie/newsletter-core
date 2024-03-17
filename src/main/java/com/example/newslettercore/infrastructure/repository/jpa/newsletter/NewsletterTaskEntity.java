package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import com.example.newslettercore.domain.newsletter.model.NewsletterTaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "newsletter_task")
public class NewsletterTaskEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "newsletter_task_id")
    private String id;

    @Column(name = "newsletter_id", unique = true, nullable = false)
    private String newsletterId;

    @Column(name = "next_trigger", nullable = false)
    private LocalDateTime nextTrigger;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private NewsletterTaskStatus newsletterTaskStatus;

    @Version
    private Long version;
}
