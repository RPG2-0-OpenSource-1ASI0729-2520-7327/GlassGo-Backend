package com.glassgo.platform.planning.domain.model.commands;

public record SubmitOrderCommand(Long orderId) {
    public SubmitOrderCommand {
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive");
        }
    }
}
