package com.glassgo.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

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
