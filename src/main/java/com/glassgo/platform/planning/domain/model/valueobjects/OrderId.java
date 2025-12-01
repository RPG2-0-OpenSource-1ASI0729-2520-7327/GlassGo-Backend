package com.glassgo.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the unique identifier for an order in the planning domain.
 * Ensures that the order ID is a positive long value.
 */
@Embeddable
public record OrderId(Long value) {
    public OrderId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("OrderId value cannot be null or negative");
        }
    }

    public OrderId() {
        this(0L);
    }
}
