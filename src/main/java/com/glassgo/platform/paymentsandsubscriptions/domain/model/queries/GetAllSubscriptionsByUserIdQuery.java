package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

public record GetAllSubscriptionsByUserIdQuery(Long userId) {
    public GetAllSubscriptionsByUserIdQuery {
        if (userId == null)
            throw new IllegalArgumentException("userId cannot be null");
    }
}
