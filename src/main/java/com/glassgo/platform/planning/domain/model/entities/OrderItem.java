package com.glassgo.platform.planning.domain.model.entities;

import com.glassgo.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * OrderItem is a part of the Order aggregate in Domain-Driven Design (DDD).
 * It represents an item in a customer's order, containing product details,
 * quantity, unit price, and total price. The OrderItem is linked to an Order
 * through a many-to-one relationship.
 */
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

    /**
     * Constructs a new OrderItem with the specified product details.
     * Automatically calculates the total price based on quantity and unit price.
     *
     * @param productName the name of the product
     * @param quantity the quantity of the product
     * @param unitPrice the unit price of the product
     * @param description the description of the product
     */
    public OrderItem(String productName, Integer quantity, BigDecimal unitPrice, String description) {
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;
        calculateTotalPrice();
    }

    /**
     * Calculates the total price of the order item based on quantity and unit price.
     */
    public void calculateTotalPrice() {
        if (quantity != null && unitPrice != null) {
            this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

    /**
     * Updates the quantity of the order item and recalculates the total price.
     *
     * @param newQuantity the new quantity
     */
    public void updateQuantity(Integer newQuantity) {
        this.quantity = newQuantity;
        calculateTotalPrice();
    }

    /**
     * Updates the unit price of the order item and recalculates the total price.
     *
     * @param newUnitPrice the new unit price
     */
    public void updateUnitPrice(BigDecimal newUnitPrice) {
        this.unitPrice = newUnitPrice;
        calculateTotalPrice();
    }

    public void setOrder(com.glassgo.platform.planning.domain.model.aggregates.Order order) {
        this.order = order;
    }
}
