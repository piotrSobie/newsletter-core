package com.example.newslettercore.application.rest.subscription.model;

import lombok.Data;

@Data
public class SubscriptionResponse {

    private String id;

    private String userId;

    private String newsletterId;
}