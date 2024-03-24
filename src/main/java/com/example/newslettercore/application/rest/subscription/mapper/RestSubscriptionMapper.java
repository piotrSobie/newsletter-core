package com.example.newslettercore.application.rest.subscription.mapper;

import com.example.newslettercore.application.rest.subscription.model.SubscriptionResponse;
import com.example.newslettercore.domain.subscription.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface RestSubscriptionMapper {

    RestSubscriptionMapper getMapper = Mappers.getMapper(RestSubscriptionMapper.class);

    SubscriptionResponse subscriptionToSubscriptionResponse(Subscription subscription);

    Collection<SubscriptionResponse> subscriptionsToSubscriptionResponses(Collection<Subscription> subscriptions);
}
