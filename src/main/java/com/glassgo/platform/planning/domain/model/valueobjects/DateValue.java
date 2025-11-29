package com.glassgo.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public record DateValue(LocalDate value) {
    public DateValue {
        if (value == null) {
            throw new IllegalArgumentException("Date value cannot be null");
        }
    }

    public DateValue() {
        this(LocalDate.now());
    }
}
