package com.glassgo.platform.planning.interfaces.rest.resources;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    public record DeliveryInfoResource(
        String deliveryDate,
        String deliveryTime,
        String address,
        String instructions
    ) {}

    public record OrderItemResource(
        Long id,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice,
        String description
    ) {}
}
