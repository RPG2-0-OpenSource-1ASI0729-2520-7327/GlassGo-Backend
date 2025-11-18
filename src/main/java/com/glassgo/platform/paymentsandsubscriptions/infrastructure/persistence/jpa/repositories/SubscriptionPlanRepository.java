package com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for SubscriptionPlan aggregates.
 */
@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

}
