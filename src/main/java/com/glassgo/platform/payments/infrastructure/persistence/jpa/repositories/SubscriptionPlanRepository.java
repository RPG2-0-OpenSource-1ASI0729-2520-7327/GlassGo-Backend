package com.glassgo.platform.payments.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.payments.domain.model.aggregates.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for SubscriptionPlan aggregates.
 */
@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

}
