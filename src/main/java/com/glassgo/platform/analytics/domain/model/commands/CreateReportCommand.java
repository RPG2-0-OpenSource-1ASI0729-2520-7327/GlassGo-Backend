package com.glassgo.platform.analytics.domain.model.commands;

import java.time.LocalDateTime;

/**
 * Command to create a new Order Tracking Record.
 * <p>
 * This command carries the essential timestamps for an order's lifecycle,
 * used to create a new record in the analytics domain.
 * </p>
 *
 * @param orderId            The unique identifier of the order.
 * @param createdAt          The timestamp when the order was created.
 * @param packagingStartedAt The timestamp when packaging began.
 * @param shippedAt          The timestamp when the order was shipped.
 * @param receivedAt         The timestamp when the order was received.
 */
public record CreateReportCommand( // Renamed from CreateReportCommand to CreateRecordCommand
        String orderId,
        LocalDateTime createdAt,
        LocalDateTime packagingStartedAt,
        LocalDateTime shippedAt,
        LocalDateTime receivedAt) {
    public CreateReportCommand {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId must not be null or blank");
        }
        // Timestamps can be null if not yet available, so no validation here.
    }
}
