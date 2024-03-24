package com.example.newslettercore.infrastructure.repository.jpa.subscription;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "subscription_id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "newsletter_id")
    private String newsletterId;
}
