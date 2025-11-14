package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

public record GetAllSubscriptionsByStatusQuery(String status) {
    public GetAllSubscriptionsByStatusQuery {
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("status cannot be null or blank");
    }
}
