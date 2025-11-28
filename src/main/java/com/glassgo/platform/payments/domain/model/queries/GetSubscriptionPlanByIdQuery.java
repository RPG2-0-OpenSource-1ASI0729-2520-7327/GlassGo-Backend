package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to fetch a Subscription Plan by id.
 */
public record GetSubscriptionPlanByIdQuery(Long subscriptionPlanId) {
    public GetSubscriptionPlanByIdQuery {
        if (subscriptionPlanId == null)
            throw new IllegalArgumentException("subscriptionPlanId cannot be null");
    }
}
