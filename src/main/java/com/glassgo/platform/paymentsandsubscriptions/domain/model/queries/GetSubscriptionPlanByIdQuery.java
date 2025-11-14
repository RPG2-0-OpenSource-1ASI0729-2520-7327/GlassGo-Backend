package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

public record GetSubscriptionPlanByIdQuery(Long subscriptionPlanId) {
    public GetSubscriptionPlanByIdQuery {
        if (subscriptionPlanId == null)
            throw new IllegalArgumentException("subscriptionPlanId cannot be null");
    }
}
