package com.glassgo.platform.analytics.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * Resource for creating a new Order Tracking Record.
 * <p>
 * This record encapsulates the specific timestamps related to an order's lifecycle,
 * allowing clients to submit detailed tracking information.
 * </p>
 *
 * @param orderId            The unique identifier of the order being tracked.
 * @param createdAt          The timestamp when the order was initially created.
 * @param packagingStartedAt The timestamp when the order started being packaged.
 * @param shippedAt          The timestamp when the order was shipped/dispatched.
 * @param receivedAt         The timestamp when the order was received by the customer.
 */
public record CreateReportResource( // Renamed from CreateReportResource to CreateRecordResource
        String orderId,
        LocalDateTime createdAt,
        LocalDateTime packagingStartedAt,
        LocalDateTime shippedAt,
        LocalDateTime receivedAt) {}
