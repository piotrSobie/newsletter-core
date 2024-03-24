package com.example.newslettercore.domain.subscription.service;

import com.example.newslettercore.domain.subscription.model.Subscription;
import com.example.newslettercore.domain.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(String userId, String newsletterId) {

        Subscription subscription = new Subscription(userId, newsletterId);
        return subscriptionRepository.save(subscription);
    }

    public Collection<Subscription> getSubscriptions(String userId) {

        return subscriptionRepository.getSubscriptions(userId);
    }

    public void deleteSubscription(String subscriptionId) {

        subscriptionRepository.delete(subscriptionId);
    }
}
