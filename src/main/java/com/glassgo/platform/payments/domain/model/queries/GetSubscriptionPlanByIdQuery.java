package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to fetch a Subscription Plan by id.
 *
 * @param subscriptionPlanId the ID of the subscription plan to retrieve
 */
public record GetSubscriptionPlanByIdQuery(Long subscriptionPlanId) {
    public GetSubscriptionPlanByIdQuery {
        if (subscriptionPlanId == null)
            throw new IllegalArgumentException("subscriptionPlanId cannot be null");
    }
}
