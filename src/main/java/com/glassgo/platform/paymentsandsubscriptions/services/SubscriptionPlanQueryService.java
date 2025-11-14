package com.glassgo.platform.paymentsandsubscriptions.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.SubscriptionPlan;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.GetAllSubscriptionPlansQuery;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.GetSubscriptionPlanByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SubscriptionPlanQueryService {
    Optional<SubscriptionPlan> handle(GetSubscriptionPlanByIdQuery query);
    List<SubscriptionPlan> handle(GetAllSubscriptionPlansQuery query);
}
