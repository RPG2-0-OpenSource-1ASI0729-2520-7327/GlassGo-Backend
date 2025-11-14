package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

public record GetAllSubscriptionsByPlanIdQuery(Long planId) {
    public GetAllSubscriptionsByPlanIdQuery {
        if (planId == null)
            throw new IllegalArgumentException("planId cannot be null");
    }
}
