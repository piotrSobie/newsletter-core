package com.example.newslettercore.infrastructure.repository.jpa.subscription;

import com.example.newslettercore.domain.subscription.model.Subscription;
import com.example.newslettercore.domain.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaSubscriptionRepository implements SubscriptionRepository {

    private final SpringDataJpaSubscriptionRepository subscriptionRepository;

    @Override
    public Subscription save(Subscription subscription) {

        SubscriptionEntity subscriptionEntity = JpaSubscriptionMapper.getMapper.subscriptionToSubscriptionEntity(subscription);
        SubscriptionEntity savedSubscription = subscriptionRepository.saveAndFlush(subscriptionEntity);
        return JpaSubscriptionMapper.getMapper.subscriptionEntityToSubscription(savedSubscription);
    }

    @Override
    public Collection<Subscription> getSubscriptions(String userId) {

        Collection<SubscriptionEntity> foundSubscriptions = subscriptionRepository.findAllByUserId(userId);
        return JpaSubscriptionMapper.getMapper.subscriptionEntitiesToSubscriptions(foundSubscriptions);
    }

    @Override
    public void delete(String subscriptionId) {

        subscriptionRepository.deleteById(subscriptionId);
    }

    @Override
    public List<String> findSubscriptionIdsByNewsletterId(String newsletterId) {

        return subscriptionRepository.findSubscriptionIdsByNewsletterId(newsletterId);
    }
}
