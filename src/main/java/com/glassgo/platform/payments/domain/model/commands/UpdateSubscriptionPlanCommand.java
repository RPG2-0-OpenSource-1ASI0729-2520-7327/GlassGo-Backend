package com.glassgo.platform.payments.domain.model.commands;

/**
 * Command to update an existing Subscription Plan.
 *
 * @param subscriptionPlanId the ID of the subscription plan to update
 * @param name               the new name
 * @param description        the new description
 * @param price              the new price
 * @param durationMonths     the new duration in months
 */
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
