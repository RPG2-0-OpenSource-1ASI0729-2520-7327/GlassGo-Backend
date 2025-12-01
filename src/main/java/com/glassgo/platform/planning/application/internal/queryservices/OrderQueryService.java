package com.glassgo.platform.planning.application.internal.queryservices;

import com.glassgo.platform.planning.domain.model.aggregates.Order;
import com.glassgo.platform.planning.domain.model.aggregates.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class for querying orders in the application.
 * Provides methods to retrieve orders based on various criteria such as order ID, order number,
 * customer name, customer email, status, and delivery date.
 * <p>
 * This class acts as an intermediary between the presentation layer and the domain layer,
 * facilitating the retrieval of order data as per the business requirements.
 * </p>
 */
@Service
public class OrderQueryService {

    private final OrderRepository orderRepository;

    public OrderQueryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Optional<Order> getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public List<Order> getOrdersByCustomerName(String customerName) {
        return orderRepository.findByCustomerName(customerName);
    }

    public List<Order> getOrdersByCustomerEmail(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }

    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByDeliveryDate(LocalDate deliveryDate) {
        return orderRepository.findByDeliveryDate(deliveryDate);
    }

    public List<Order> searchOrdersByCustomer(String searchTerm) {
        return orderRepository.findByCustomerNameOrEmail(searchTerm, searchTerm);
    }

    public List<Order> getActiveOrders() {
        List<Order.OrderStatus> activeStatuses = List.of(
            Order.OrderStatus.PENDING,
            Order.OrderStatus.SUBMITTED,
            Order.OrderStatus.CONFIRMED,
            Order.OrderStatus.IN_PREPARATION,
            Order.OrderStatus.OUT_FOR_DELIVERY
        );
        return orderRepository.findByStatusIn(activeStatuses);
    }

    public List<Order> getCompletedOrders() {
        List<Order.OrderStatus> completedStatuses = List.of(
            Order.OrderStatus.DELIVERED,
            Order.OrderStatus.CANCELLED
        );
        return orderRepository.findByStatusIn(completedStatuses);
    }
}
