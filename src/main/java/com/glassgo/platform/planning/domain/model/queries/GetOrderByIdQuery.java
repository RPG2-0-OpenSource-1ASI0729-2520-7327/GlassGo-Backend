package com.glassgo.platform.planning.domain.model.queries;

/**
 * Query class for retrieving an order by its ID.
 * This class is part of the domain model in the CQRS (Command Query Responsibility Segregation) pattern.
 *
 * @param orderId the ID of the order to be retrieved, must be a positive number
 */
public record GetOrderByIdQuery(Long orderId) {
    public GetOrderByIdQuery {
        if (orderId == null || orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive");
        }
    }
}
