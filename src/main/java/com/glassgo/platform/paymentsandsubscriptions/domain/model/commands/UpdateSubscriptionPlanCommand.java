package com.glassgo.platform.paymentsandsubscriptions.domain.model.commands;

public record UpdateSubscriptionPlanCommand(
        Long subscriptionPlanId,
        String name,
        String description,
        Double price,
        Integer durationMonths
) {
    public UpdateSubscriptionPlanCommand {
        if (subscriptionPlanId == null)
            throw new IllegalArgumentException("subscriptionPlanId cannot be null");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or blank");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description cannot be null or blank");
        if (price == null)
            throw new IllegalArgumentException("price cannot be null");
        if (durationMonths == null)
            throw new IllegalArgumentException("durationMonths cannot be null");
    }
}
