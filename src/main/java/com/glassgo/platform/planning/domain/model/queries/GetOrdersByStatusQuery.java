package com.glassgo.platform.planning.domain.model.queries;

import com.glassgo.platform.planning.domain.model.aggregates.Order;

/**
 * Query class for retrieving orders by their status.
 * This class is part of the domain model in the CQRS pattern.
 *
 * @param status the status of the orders to be retrieved, must not be null
 */
public record GetOrdersByStatusQuery(Order.OrderStatus status) {
    public GetOrdersByStatusQuery {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
    }
}
