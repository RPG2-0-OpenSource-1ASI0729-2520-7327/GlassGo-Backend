package com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByPlanId(Long planId);
    List<Subscription> findByStatus(String status);
    List<Subscription> findByUserId(Long userId);
}
