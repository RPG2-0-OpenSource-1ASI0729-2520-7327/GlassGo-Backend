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

    public void addOrderItem(OrderItem orderItem) {
        items.add(orderItem);
        orderItem.setOrder(this);
        calculateTotalAmount();
    }

    public void removeOrderItem(OrderItem orderItem) {
        items.remove(orderItem);
        calculateTotalAmount();
    }

    public void calculateTotalAmount() {
        this.totalAmount = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void submit() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot submit an order without items");
        }
        this.status = OrderStatus.SUBMITTED;
    }

    public void confirm() {
        if (status != OrderStatus.SUBMITTED) {
            throw new IllegalStateException("Can only confirm submitted orders");
        }
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        if (status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cannot cancel delivered or already cancelled orders");
        }
        this.status = OrderStatus.CANCELLED;
    }

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
