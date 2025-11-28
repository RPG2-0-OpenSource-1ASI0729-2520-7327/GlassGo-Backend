package com.glassgo.platform.payments.domain.model.services;

import com.glassgo.platform.payments.domain.model.aggregates.Subscription;
import com.glassgo.platform.payments.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Query service that exposes read-only operations related to Subscriptions.
 * <p>
 * Implementations should focus on retrieval operations and return domain
 * aggregates. Each `handle` method receives a query object that describes
 * the selection criteria.
 */
public interface SubscriptionQueryService {
    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
    List<Subscription> handle(GetAllSubscriptionsQuery query);
    List<Subscription> handle(GetAllSubscriptionsByPlanIdQuery query);
    List<Subscription> handle(GetAllSubscriptionsByStatusQuery query);
    List<Subscription> handle(GetAllSubscriptionsByUserIdQuery query);
}
