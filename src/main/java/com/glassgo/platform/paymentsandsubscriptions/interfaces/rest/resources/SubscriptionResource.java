package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

import java.util.Date;

public record SubscriptionResource(Long id, Long userId, Long planId, String status, Date startDate, Date endDate, Boolean autoRenew) {
}
