package com.glassgo.platform.paymentsandsubscriptions.domain.model.commands;

/**
 * Command to delete a Subscription.
 */
public record DeleteSubscriptionCommand(Long subscriptionId) {
    public DeleteSubscriptionCommand {
        if (subscriptionId == null)
            throw new IllegalArgumentException("subscriptionId cannot be null");
    }
}
