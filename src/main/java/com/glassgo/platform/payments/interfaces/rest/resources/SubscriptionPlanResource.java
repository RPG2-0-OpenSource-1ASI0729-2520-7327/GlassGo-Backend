package com.glassgo.platform.payments.interfaces.rest.resources;

/**
 * Representation of a Subscription Plan exposed by the REST API.
 * <p>
 * Use this resource as a response object for endpoints that return subscription plan data.
 *
 * @param id             the subscription plan ID
 * @param name           the name of the plan
 * @param description    the description of the plan
 * @param price          the price of the plan
 * @param durationMonths the duration in months
 */
public record SubscriptionPlanResource(
        Long id,
        String name,
        String description,
        Double price,
        Integer durationMonths
) {
}
