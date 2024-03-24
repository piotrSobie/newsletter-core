package com.example.newslettercore.infrastructure.repository.jpa.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SpringDataJpaSubscriptionRepository extends JpaRepository<SubscriptionEntity, String> {

    Collection<SubscriptionEntity> findAllByUserId(String userId);

    @Query(value = "select s.id from SubscriptionEntity s where s.newsletterId = :newsletterId")
    List<String> findSubscriptionIdsByNewsletterId(String newsletterId);
}
