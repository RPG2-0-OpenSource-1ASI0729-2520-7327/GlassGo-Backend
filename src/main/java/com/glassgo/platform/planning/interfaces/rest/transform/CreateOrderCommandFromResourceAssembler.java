package com.glassgo.platform.planning.interfaces.rest.transform;

import com.glassgo.platform.planning.domain.model.commands.CreateOrderCommand;
import com.glassgo.platform.planning.domain.model.valueobjects.DeliveryInfo;
import com.glassgo.platform.planning.interfaces.rest.resources.CreateOrderResource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class CreateOrderCommandFromResourceAssembler {

    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource) {
        var deliveryInfo = toDeliveryInfo(resource.deliveryInfo());

        var items = resource.items().stream()
            .map(item -> new CreateOrderCommand.OrderItemRequest(
                item.productName(),
                item.quantity(),
                item.unitPrice(),
                item.description()
            ))
            .collect(Collectors.toList());

        return new CreateOrderCommand(
            resource.customerName(),
            resource.customerEmail(),
            resource.customerPhone(),
            deliveryInfo,
            items,
            resource.notes()
        );
    }

    private static DeliveryInfo toDeliveryInfo(CreateOrderResource.DeliveryInfoResource resource) {
        LocalDate deliveryDate = LocalDate.parse(resource.deliveryDate());
        LocalTime deliveryTime = LocalTime.parse(resource.deliveryTime());

        return new DeliveryInfo(
            deliveryDate,
            deliveryTime,
            resource.address(),
            resource.instructions()
        );
    }
}
