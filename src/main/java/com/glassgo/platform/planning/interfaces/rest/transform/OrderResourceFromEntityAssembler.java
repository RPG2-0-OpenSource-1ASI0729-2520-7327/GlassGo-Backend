package com.glassgo.platform.planning.interfaces.rest.transform;

import com.glassgo.platform.planning.domain.model.aggregates.Order;
import com.glassgo.platform.planning.domain.model.entities.OrderItem;
import com.glassgo.platform.planning.interfaces.rest.resources.OrderResource;

import java.util.stream.Collectors;

/**
 * Assembler class for transforming Order domain entities into OrderResource objects.
 * Used in the REST layer to convert domain objects to resources for API responses.
 */
public class OrderResourceFromEntityAssembler {

    public static OrderResource toResourceFromEntity(Order order) {
        var deliveryInfo = new OrderResource.DeliveryInfoResource(
            order.getDeliveryInfo().deliveryDate().toString(),
            order.getDeliveryInfo().deliveryTime().toString(),
            order.getDeliveryInfo().address(),
            order.getDeliveryInfo().instructions()
        );

        var items = order.getItems().stream()
            .map(OrderResourceFromEntityAssembler::toOrderItemResource)
            .collect(Collectors.toList());

        return new OrderResource(
            order.getId(),
            order.getOrderNumber(),
            order.getStatus().toString(),
            order.getCustomerName(),
            order.getCustomerEmail(),
            order.getCustomerPhone(),
            deliveryInfo,
            items,
            order.getTotalAmount(),
            order.getNotes(),
            order.getCreatedAt(),
            order.getUpdatedAt()
        );
    }

    private static OrderResource.OrderItemResource toOrderItemResource(OrderItem item) {
        return new OrderResource.OrderItemResource(
            item.getId(),
            item.getProductName(),
            item.getQuantity(),
            item.getUnitPrice(),
            item.getTotalPrice(),
            item.getDescription()
        );
    }
}
