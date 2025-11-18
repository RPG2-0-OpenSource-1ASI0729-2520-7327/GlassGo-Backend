package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

/**
 * Representation of a Subscription Plan exposed by the REST API.
 * <p>
 * Use this resource as a response object for endpoints that return subscription plan data.
 */
public record SubscriptionPlanResource(
        Long id,
        String name,
        String description,
        Double price,
        Integer durationMonths
) {
}
