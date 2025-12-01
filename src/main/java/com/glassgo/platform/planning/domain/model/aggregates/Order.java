package com.glassgo.platform.planning.domain.model.aggregates;

import com.glassgo.platform.planning.domain.model.entities.OrderItem;
import com.glassgo.platform.planning.domain.model.valueobjects.DeliveryInfo;
import com.glassgo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Order aggregate root for order management in the planning domain.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Order extends AuditableAbstractAggregateRoot<Order> {

    @Column(nullable = false, length = 50)
    private String orderNumber;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(nullable = false, length = 100)
    private String customerName;

    @Column(length = 100)
    private String customerEmail;

    @Column(length = 20)
    private String customerPhone;

    @Embedded
    private DeliveryInfo deliveryInfo;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(length = 500)
    private String notes;

    /**
     * Constructs a new Order with the specified details.
     * Initializes the order status to PENDING and total amount to zero.
     *
     * @param orderNumber the unique order number
     * @param customerName the name of the customer
     * @param customerEmail the email of the customer
     * @param customerPhone the phone number of the customer
     * @param deliveryInfo the delivery information
     */
    public Order(String orderNumber, String customerName, String customerEmail,
                String customerPhone, DeliveryInfo deliveryInfo) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.deliveryInfo = deliveryInfo;
        this.status = OrderStatus.PENDING;
        this.totalAmount = BigDecimal.ZERO;
    }

    /**
     * Adds an order item to the order and recalculates the total amount.
     *
     * @param orderItem the order item to add
     */
    public void addOrderItem(OrderItem orderItem) {
        items.add(orderItem);
        orderItem.setOrder(this);
        calculateTotalAmount();
    }

    /**
     * Removes an order item from the order and recalculates the total amount.
     *
     * @param orderItem the order item to remove
     */
    public void removeOrderItem(OrderItem orderItem) {
        items.remove(orderItem);
        calculateTotalAmount();
    }

    /**
     * Calculates the total amount of the order based on the sum of all order items' total prices.
     */
    public void calculateTotalAmount() {
        this.totalAmount = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Submits the order, changing its status to SUBMITTED.
     * Throws an exception if the order has no items.
     *
     * @throws IllegalStateException if the order has no items
     */
    public void submit() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot submit an order without items");
        }
        this.status = OrderStatus.SUBMITTED;
    }

    /**
     * Confirms the order, changing its status to CONFIRMED.
     * Throws an exception if the order is not in SUBMITTED status.
     *
     * @throws IllegalStateException if the order is not submitted
     */
    public void confirm() {
        if (status != OrderStatus.SUBMITTED) {
            throw new IllegalStateException("Can only confirm submitted orders");
        }
        this.status = OrderStatus.CONFIRMED;
    }

    /**
     * Cancels the order, changing its status to CANCELLED.
     * Throws an exception if the order is already delivered or cancelled.
     *
     * @throws IllegalStateException if the order is delivered or already cancelled
     */
    public void cancel() {
        if (status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cannot cancel delivered or already cancelled orders");
        }
        this.status = OrderStatus.CANCELLED;
    }

    /**
     * Marks the order as delivered, changing its status to DELIVERED.
     * Throws an exception if the order is not in CONFIRMED status.
     *
     * @throws IllegalStateException if the order is not confirmed
     */
    public void markAsDelivered() {
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Can only deliver confirmed orders");
        }
        this.status = OrderStatus.DELIVERED;
    }

    public enum OrderStatus {
        PENDING,
        SUBMITTED,
        CONFIRMED,
        IN_PREPARATION,
        OUT_FOR_DELIVERY,
        DELIVERED,
        CANCELLED
    }
}
