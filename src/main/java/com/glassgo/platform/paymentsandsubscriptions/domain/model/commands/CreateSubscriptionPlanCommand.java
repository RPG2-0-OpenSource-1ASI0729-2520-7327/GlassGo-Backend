package com.glassgo.platform.paymentsandsubscriptions.domain.model.commands;

public record CreateSubscriptionPlanCommand(
        String name,
        String description,
        Double price,
        Integer durationMonths
) {
    public CreateSubscriptionPlanCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description cannot be null");
        if (price == null)
            throw new IllegalArgumentException("price cannot be null");
        if (durationMonths == null)
            throw new IllegalArgumentException("durationMonths cannot be null");
    }
}
