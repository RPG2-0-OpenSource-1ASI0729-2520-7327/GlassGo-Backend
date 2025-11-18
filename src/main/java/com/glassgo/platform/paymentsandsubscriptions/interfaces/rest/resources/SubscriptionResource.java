package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

import java.util.Date;

/**
 * Representation of a Subscription returned by the REST API.
 * <p>
 * This immutable resource is used as a response object for subscription-related
 * endpoints. It contains the relevant subscription fields exposed to API clients.
 */
public record SubscriptionResource(Long id, Long userId, Long planId, String status, Date startDate, Date endDate, Boolean autoRenew) {
}
