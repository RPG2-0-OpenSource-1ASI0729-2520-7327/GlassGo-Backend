package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to obtain all subscriptions belonging to a specific user.
 */
public record GetAllSubscriptionsByUserIdQuery(Long userId) {
    public GetAllSubscriptionsByUserIdQuery {
        if (userId == null)
            throw new IllegalArgumentException("userId cannot be null");
    }
}
