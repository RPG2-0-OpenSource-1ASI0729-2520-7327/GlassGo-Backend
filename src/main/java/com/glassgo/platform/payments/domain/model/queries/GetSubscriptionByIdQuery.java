package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to obtain a Subscription by its identifier.
 */
public record GetSubscriptionByIdQuery(Long subscriptionId) {
    public GetSubscriptionByIdQuery {
        if (subscriptionId == null)
            throw new IllegalArgumentException("subscriptionId cannot be null");
    }
}
