package com.glassgo.platform.payments.domain.model.commands;

import java.util.Date;

/**
 * Command to create a new Subscription in the domain layer.
 * <p>
 * This record carries the data required by the application service to create
 * a subscription aggregate.
 *
 * @param userId    the ID of the user
 * @param planId    the ID of the subscription plan
 * @param status    the status of the subscription
 * @param startDate the start date of the subscription
 * @param endDate   the end date of the subscription
 * @param autoRenew whether the subscription auto-renews
 */
public record CreateSubscriptionCommand(
        Long userId,
        Long planId,
        String status,
        Date startDate,
        Date endDate,
        Boolean autoRenew
) {
    public CreateSubscriptionCommand {
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
