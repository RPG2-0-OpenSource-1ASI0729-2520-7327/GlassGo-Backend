package com.glassgo.platform.payments.domain.model.services;

import com.glassgo.platform.payments.domain.model.aggregates.SubscriptionPlan;
import com.glassgo.platform.payments.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Query service for read-only operations related to Subscription Plans.
 */
public interface SubscriptionPlanQueryService {
    Optional<SubscriptionPlan> handle(GetSubscriptionPlanByIdQuery query);
    List<SubscriptionPlan> handle(GetAllSubscriptionPlansQuery query);
}
