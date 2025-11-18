package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

/**
 * Resource used to create a new Subscription Plan via REST API.
 * <p>
 * This record represents the request payload for creating plans and validates
 * required fields in the compact constructor.
 */
public record CreateSubscriptionPlanResource(
        String name,
        String description,
        Double price,
        Integer durationMonths
) {
    public CreateSubscriptionPlanResource {
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
