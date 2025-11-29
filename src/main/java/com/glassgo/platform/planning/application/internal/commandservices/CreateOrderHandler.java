package com.glassgo.platform.planning.application.internal.commandservices;

import com.glassgo.platform.planning.domain.model.aggregates.Order;
import com.glassgo.platform.planning.domain.model.aggregates.OrderRepository;
import com.glassgo.platform.planning.domain.model.commands.CreateOrderCommand;
import com.glassgo.platform.planning.domain.model.commands.SubmitOrderCommand;
import com.glassgo.platform.planning.domain.model.entities.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateOrderHandler {

    private final OrderRepository orderRepository;

    public CreateOrderHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order handle(CreateOrderCommand command) {
        // Generate unique order number
        String orderNumber = generateOrderNumber();

        // Create the order
        Order order = new Order(
            orderNumber,
            command.customerName(),
            command.customerEmail(),
            command.customerPhone(),
            command.deliveryInfo()
        );

        // Add items to the order
        command.items().forEach(itemRequest -> {
            OrderItem orderItem = new OrderItem(
                itemRequest.productName(),
                itemRequest.quantity(),
                itemRequest.unitPrice(),
                itemRequest.description()
            );
            order.addOrderItem(orderItem);
        });

        // Set notes if provided
        if (command.notes() != null && !command.notes().trim().isEmpty()) {
            order.setNotes(command.notes());
        }

        // Save and return the order
        return orderRepository.save(order);
    }

    @Transactional
    public Order handle(SubmitOrderCommand command) {
        Order order = orderRepository.findById(command.orderId())
            .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + command.orderId()));

        order.submit();
        return orderRepository.save(order);
    }

    private String generateOrderNumber() {
        String orderNumber;
        do {
            orderNumber = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (orderRepository.existsByOrderNumber(orderNumber));

        return orderNumber;
    }
}
