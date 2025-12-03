package com.glassgo.platform.planning.domain.model.queries;

/**
 * Query class for retrieving an order by its order number.
 * This class is part of the domain model in the planning context.
 *
 * @param orderNumber the order number of the order to be retrieved
 */
public record GetOrderByOrderNumberQuery(String orderNumber) {
    public GetOrderByOrderNumberQuery {
        if (orderNumber == null || orderNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Order number cannot be null or empty");
        }
    }
}
