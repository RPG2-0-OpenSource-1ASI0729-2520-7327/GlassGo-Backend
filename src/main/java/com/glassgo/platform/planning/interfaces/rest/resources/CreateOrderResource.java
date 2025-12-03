package com.glassgo.platform.planning.interfaces.rest.resources;


import java.math.BigDecimal;
import java.util.List;

/**
 * Resource representing the data required to create a new order in the planning domain.
 * This resource encapsulates customer information, delivery details, order items, and additional notes.
 */
public record CreateOrderResource(
    String customerName,
    String customerEmail,
    String customerPhone,
    DeliveryInfoResource deliveryInfo,
    List<OrderItemResource> items,
    String notes
) {
    /**
     * Resource representing delivery information for an order.
     */
    public record DeliveryInfoResource(
        String deliveryDate,
        String deliveryTime,
        String address,
        String instructions
    ) {}

    /**
     * Resource representing an item in an order.
     */
    public record OrderItemResource(
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        String description
    ) {}
}
