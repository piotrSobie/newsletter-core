package com.example.newslettercore.infrastructure.repository.jpa.subscription;

import com.example.newslettercore.domain.subscription.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface JpaSubscriptionMapper {

    JpaSubscriptionMapper getMapper = Mappers.getMapper(JpaSubscriptionMapper.class);

    SubscriptionEntity subscriptionToSubscriptionEntity(Subscription subscription);

    default Subscription subscriptionEntityToSubscription(SubscriptionEntity subscriptionEntity) {

        return new Subscription(subscriptionEntity.getId(), subscriptionEntity.getUserId(), subscriptionEntity.getNewsletterId());
    }

    Collection<Subscription> subscriptionEntitiesToSubscriptions(Collection<SubscriptionEntity> subscriptionEntities);
}
