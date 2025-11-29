package com.glassgo.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public record TimeValue(LocalTime value) {
    public TimeValue {
        if (value == null) {
            throw new IllegalArgumentException("Time value cannot be null");
        }
    }

    public TimeValue() {
        this(LocalTime.now());
    }
}
