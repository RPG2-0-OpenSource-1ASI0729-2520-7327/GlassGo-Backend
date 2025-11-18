package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

public record SubscriptionPlanResource(
        Long id,
        String name,
        String description,
        Double price,
        Integer durationMonths
) {
}
