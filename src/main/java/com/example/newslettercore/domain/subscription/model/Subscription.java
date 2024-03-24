package com.example.newslettercore.domain.subscription.model;

import lombok.Getter;

@Getter
public class Subscription {

    private String id;

    private String userId;

    private String newsletterId;

    public Subscription(String id, String userId, String newsletterId) {

        this.id = id;
        this.userId = userId;
        this.newsletterId = newsletterId;
    }

    public Subscription(String userId, String newsletterId) {

        this.userId = userId;
        this.newsletterId = newsletterId;
    }
}
