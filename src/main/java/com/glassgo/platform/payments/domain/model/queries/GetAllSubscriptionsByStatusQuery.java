package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to fetch all subscriptions by their status.
 */
public record GetAllSubscriptionsByStatusQuery(String status) {
    public GetAllSubscriptionsByStatusQuery {
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("status cannot be null or blank");
    }
}
