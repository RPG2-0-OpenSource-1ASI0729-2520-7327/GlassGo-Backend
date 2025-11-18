package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

/**
 * Query to fetch all subscriptions matching a given plan id.
 */
public record GetAllSubscriptionsByPlanIdQuery(Long planId) {
    public GetAllSubscriptionsByPlanIdQuery {
        if (planId == null)
            throw new IllegalArgumentException("planId cannot be null");
    }
}
