package com.glassgo.platform.planning.domain.model.commands;

import com.glassgo.platform.planning.domain.model.valueobjects.DeliveryInfo;

import java.util.List;

public record CreateOrderCommand(
    String customerName,
    String customerEmail,
    String customerPhone,
    DeliveryInfo deliveryInfo,
    List<OrderItemRequest> items,
    String notes
) {
    public CreateOrderCommand {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        if (deliveryInfo == null) {
            throw new IllegalArgumentException("Delivery info cannot be null");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
    }

    public record OrderItemRequest(
        String productName,
        Integer quantity,
        java.math.BigDecimal unitPrice,
        String description
    ) {
        public OrderItemRequest {
            if (productName == null || productName.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be null or empty");
            }
            if (quantity == null || quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            if (unitPrice == null || unitPrice.compareTo(java.math.BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Unit price cannot be negative");
            }
        }
    }
}
