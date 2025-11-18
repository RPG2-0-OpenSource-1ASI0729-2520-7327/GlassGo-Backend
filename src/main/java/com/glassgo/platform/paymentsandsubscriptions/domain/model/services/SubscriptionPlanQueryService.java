package com.glassgo.platform.paymentsandsubscriptions.domain.model.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.SubscriptionPlan;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Query service for read-only operations related to Subscription Plans.
 */
public interface SubscriptionPlanQueryService {
    Optional<SubscriptionPlan> handle(GetSubscriptionPlanByIdQuery query);
    List<SubscriptionPlan> handle(GetAllSubscriptionPlansQuery query);
}
