package com.glassgo.platform.planning.domain.model.queries;

public record GetOrderByIdQuery(Long orderId) {
    public GetOrderByIdQuery {
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive");
        }
    }
}
