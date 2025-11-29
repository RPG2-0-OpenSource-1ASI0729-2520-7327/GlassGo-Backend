package com.glassgo.platform.planning.interfaces.rest.resources;


import java.math.BigDecimal;
import java.util.List;

public record CreateOrderResource(
    String customerName,
    String customerEmail,
    String customerPhone,
    DeliveryInfoResource deliveryInfo,
    List<OrderItemResource> items,
    String notes
) {
    public record DeliveryInfoResource(
        String deliveryDate,
        String deliveryTime,
        String address,
        String instructions
    ) {}

    public record OrderItemResource(
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        String description
    ) {}
}
