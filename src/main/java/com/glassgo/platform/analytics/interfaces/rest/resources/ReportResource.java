package com.glassgo.platform.analytics.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * Resource representing an Order Tracking Report for REST API responses.
 * <p>
 * This record provides a client-friendly view of the order tracking data, exposing
 * the unique report ID, the associated order ID, and all recorded timestamps.
 * </p>
 *
 * @param id                 The unique identifier of the report.
 * @param orderId            The unique identifier of the order being tracked.
 * @param createdAt          The timestamp when the order was initially created.
 * @param packagingStartedAt The timestamp when the order started being packaged.
 * @param shippedAt          The timestamp when the order was shipped/dispatched.
 * @param receivedAt         The timestamp when the order was received by the customer.
 */
public record ReportResource(
        Long id,
        String orderId,
        LocalDateTime createdAt,
        LocalDateTime packagingStartedAt,
        LocalDateTime receivedAt,
        LocalDateTime shippedAt) {
}
