package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

public record GetSubscriptionByIdQuery(Long subscriptionId) {
    public GetSubscriptionByIdQuery {
        if (subscriptionId == null)
            throw new IllegalArgumentException("subscriptionId cannot be null");
    }
}
