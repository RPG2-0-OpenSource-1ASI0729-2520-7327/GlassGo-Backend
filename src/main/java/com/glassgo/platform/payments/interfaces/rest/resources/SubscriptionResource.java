package com.glassgo.platform.payments.interfaces.rest.resources;

import java.util.Date;

/**
 * Representation of a Subscription returned by the REST API.
 * <p>
 * This immutable resource is used as a response object for subscription-related
 * endpoints. It contains the relevant subscription fields exposed to API clients.
 *
 * @param id        the subscription ID
 * @param userId    the user ID
 * @param planId    the plan ID
 * @param status    the subscription status
 * @param startDate the start date
 * @param endDate   the end date
 * @param autoRenew whether auto-renew is enabled
 */
public record SubscriptionResource(Long id, Long userId, Long planId, String status, Date startDate, Date endDate, Boolean autoRenew) {
}
