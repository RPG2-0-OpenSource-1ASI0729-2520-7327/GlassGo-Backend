package com.glassgo.platform.paymentsandsubscriptions.domain.model.commands;

public record DeleteSubscriptionPlanCommand(Long subscriptionPlanId) {
    public DeleteSubscriptionPlanCommand {
        if (subscriptionPlanId == null)
            throw new IllegalArgumentException("subscriptionPlanId cannot be null");
    }
}
