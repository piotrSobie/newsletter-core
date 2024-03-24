package com.example.newslettercore.application.rest.subscription.api;

import com.example.newslettercore.application.rest.subscription.mapper.RestSubscriptionMapper;
import com.example.newslettercore.application.rest.subscription.model.SubscriptionResponse;
import com.example.newslettercore.domain.subscription.model.Subscription;
import com.example.newslettercore.domain.subscription.service.SubscriptionService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = SubscriptionController.CONTROLLER_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class SubscriptionController {

    public static final String CONTROLLER_ENDPOINT = "/api/v1";
    public static final String SUBSCRIPTION_ENDPOINT = "/subscription";

    private final SubscriptionService subscriptionService;

    @PostMapping(path = SUBSCRIPTION_ENDPOINT)
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestParam(value = "userId") String userId,
                                                                   @RequestParam(value = "newsletterId") String newsletterId) {

        Subscription subscription = subscriptionService.createSubscription(userId, newsletterId);
        SubscriptionResponse subscriptionResponse = RestSubscriptionMapper.getMapper.subscriptionToSubscriptionResponse(subscription);
        return new ResponseEntity<>(subscriptionResponse, HttpStatus.CREATED);
    }

    @GetMapping(path = SUBSCRIPTION_ENDPOINT)
    public ResponseEntity<Collection<SubscriptionResponse>> getSubscriptions(@RequestParam(value = "userId") String userId) {

        Collection<Subscription> subscriptions = subscriptionService.getSubscriptions(userId);
        Collection<SubscriptionResponse> subscriptionResponses = RestSubscriptionMapper.getMapper.subscriptionsToSubscriptionResponses(subscriptions);
        return new ResponseEntity<>(subscriptionResponses, HttpStatus.OK);
    }

    @DeleteMapping(path = SUBSCRIPTION_ENDPOINT + "/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@Nonnull @PathVariable("subscriptionId") String subscriptionId) {

        subscriptionService.deleteSubscription(subscriptionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
