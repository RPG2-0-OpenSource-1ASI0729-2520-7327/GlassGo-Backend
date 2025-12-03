package com.glassgo.platform.planning.domain.model.commands;

/**
 * Command to submit an order in the system.
 * <p>
 * This command is part of the domain model in a DDD (Domain-Driven Design) approach.
 *
 * @param orderId the ID of the order to be submitted
 */
public record SubmitOrderCommand(Long orderId) {
    public SubmitOrderCommand {
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive");
        }
    }
}
