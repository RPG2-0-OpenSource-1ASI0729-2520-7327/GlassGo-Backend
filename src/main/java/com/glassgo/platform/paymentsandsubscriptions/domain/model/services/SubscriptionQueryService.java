package com.glassgo.platform.paymentsandsubscriptions.domain.model.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Subscription;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface SubscriptionQueryService {
    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
    List<Subscription> handle(GetAllSubscriptionsQuery query);
    List<Subscription> handle(GetAllSubscriptionsByPlanIdQuery query);
    List<Subscription> handle(GetAllSubscriptionsByStatusQuery query);
    List<Subscription> handle(GetAllSubscriptionsByUserIdQuery query);
}
