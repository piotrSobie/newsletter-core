package com.example.newslettercore.infrastructure.repository.jpa.newsletter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "newsletter")
public class NewsletterEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "newsletter_id")
    private String id;

    @ElementCollection
    @CollectionTable(name = "newsletter_tag", joinColumns = @JoinColumn(name = "newsletter_id"))
    @Column(name = "tag")
    private Set<String> tags;

    @Column(name = "cron_sending_frequency")
    private String cronSendingFrequency;

    @OneToMany(mappedBy = "newsletter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateEntity> templates;
}
