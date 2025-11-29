package com.glassgo.platform.planning.domain.model.queries;

public record GetOrderByOrderNumberQuery(String orderNumber) {
    public GetOrderByOrderNumberQuery {
        if (orderNumber == null || orderNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Order number cannot be null or empty");
        }
    }
}
