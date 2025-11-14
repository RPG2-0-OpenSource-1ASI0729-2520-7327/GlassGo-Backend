package com.glassgo.platform.paymentsandsubscriptions.domain.model.commands;

public record DeleteSubscriptionCommand(Long subscriptionId) {
    public DeleteSubscriptionCommand {
        if (subscriptionId == null)
            throw new IllegalArgumentException("subscriptionId cannot be null");
    }
}
