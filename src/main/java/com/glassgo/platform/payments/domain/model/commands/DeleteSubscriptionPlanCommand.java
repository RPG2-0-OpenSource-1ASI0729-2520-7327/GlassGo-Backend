package com.glassgo.platform.payments.domain.model.commands;

/**
 * Command to delete a Subscription Plan.
 */
public record DeleteSubscriptionPlanCommand(Long subscriptionPlanId) {
    public DeleteSubscriptionPlanCommand {
        if (subscriptionPlanId == null)
            throw new IllegalArgumentException("subscriptionPlanId cannot be null");
    }
}
