package com.glassgo.platform.planning.domain.model.entities;

import com.glassgo.platform.planning.domain.model.aggregates.Order;
import com.glassgo.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a record of a status change for an {@link Order}.
 * <p>
 * This entity is used to audit the lifecycle of an order by storing
 * each status transition along with a timestamp.
 * </p>
 */
@Getter
@NoArgsConstructor
@Entity
public class OrderStatusHistory extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The order to which this status history belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * The status of the order at this point in time.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Order.OrderStatus status;

    /**
     * The exact time when the status was recorded.
     */
    @Column(nullable = false)
    private LocalDateTime timestamp;

    /**
     * Creates a new instance of {@link OrderStatusHistory}.
     *
     * @param order  The associated order.
     * @param status The status to be recorded.
     */
    public OrderStatusHistory(Order order, Order.OrderStatus status) {
        this.order = order;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
