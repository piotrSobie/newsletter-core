package com.example.newslettercore.domain.subscription.repository;

import com.example.newslettercore.domain.subscription.model.Subscription;

import java.util.Collection;
import java.util.List;

public interface SubscriptionRepository {

    Subscription save(Subscription subscription);

    Collection<Subscription> getSubscriptions(String userId);

    void delete(String subscriptionId);

    List<String> findSubscriptionIdsByNewsletterId(String newsletterId);
}
