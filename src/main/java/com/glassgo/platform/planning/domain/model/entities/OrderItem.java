package com.glassgo.platform.planning.domain.model.entities;

import com.glassgo.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderItem extends AuditableModel {

    @Column(nullable = false, length = 100)
    private String productName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private com.glassgo.platform.planning.domain.model.aggregates.Order order;

    public OrderItem(String productName, Integer quantity, BigDecimal unitPrice, String description) {
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        if (quantity != null && unitPrice != null) {
            this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    public void updateQuantity(Integer newQuantity) {
        this.quantity = newQuantity;
        calculateTotalPrice();
    }

    public void updateUnitPrice(BigDecimal newUnitPrice) {
        this.unitPrice = newUnitPrice;
        calculateTotalPrice();
    }

    public void setOrder(com.glassgo.platform.planning.domain.model.aggregates.Order order) {
        this.order = order;
    }
}
