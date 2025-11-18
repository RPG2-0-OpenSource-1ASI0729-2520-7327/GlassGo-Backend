package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

import java.util.Date;

/**
 * Resource used to create a new Subscription via REST API.
 * <p>
 * This immutable record represents the payload accepted by the endpoint that
 * creates subscriptions. Field validation is performed in the compact constructor.
 * Use this resource as the request body for the corresponding POST operation.
 */
public record CreateSubscriptionResource(
        Long userId,
        Long planId,
        String status,
        Date startDate,
        Date endDate,
        Boolean autoRenew
) {
    public CreateSubscriptionResource {
        if (userId == null)
            throw new IllegalArgumentException("userId cannot be null");
        if (planId == null)
            throw new IllegalArgumentException("planId cannot be null");
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("status cannot be null or blank");
        if (startDate == null)
            throw new IllegalArgumentException("startDate cannot be null");
        if (endDate == null)
            throw new IllegalArgumentException("endDate cannot be null");
        if (autoRenew == null)
            throw new IllegalArgumentException("autoRenew cannot be null");
    }
}
