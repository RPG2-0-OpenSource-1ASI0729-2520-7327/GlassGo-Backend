package com.glassgo.platform.planning.domain.model.queries;

import com.glassgo.platform.planning.domain.model.aggregates.Order;

public record GetOrdersByStatusQuery(Order.OrderStatus status) {
    public GetOrdersByStatusQuery {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }
}
