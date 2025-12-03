package com.glassgo.platform.planning.interfaces.rest.resources;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Resource representing an order in the planning domain.
 * This resource includes all details of an order, such as customer information, status, items, and timestamps.
 */
public record OrderResource(
    Long id,
    String orderNumber,
    String status,
    String customerName,
    String customerEmail,
    String customerPhone,
    DeliveryInfoResource deliveryInfo,
    List<OrderItemResource> items,
    BigDecimal totalAmount,
    String notes,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
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
        Long id,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice,
        String description
    ) {}
}
