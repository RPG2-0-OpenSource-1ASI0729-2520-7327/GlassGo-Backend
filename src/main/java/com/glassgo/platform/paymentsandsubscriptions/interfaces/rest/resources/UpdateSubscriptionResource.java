package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

public record UpdateSubscriptionResource(
        Long subscriptionId,
        Long planId,
        String status,
        Boolean autoRenew
) {
    public UpdateSubscriptionResource {
        if (subscriptionId == null)
            throw new IllegalArgumentException("subscriptionId cannot be null");
        if (planId == null)
            throw new IllegalArgumentException("planId cannot be null");
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("status cannot be null or blank");
        if (autoRenew == null)
            throw new IllegalArgumentException("autoRenew cannot be null");
    }
}
