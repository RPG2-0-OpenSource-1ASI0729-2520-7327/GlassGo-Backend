package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

/**
 * Resource used to update an existing Subscription via REST API.
 * <p>
 * This record represents the payload accepted by the update subscription endpoint.
 * Basic validation is applied in the compact constructor to guarantee required fields.
 */
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
