package com.glassgo.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

/**
 * Value object representing a date in the planning domain.
 * Ensures that the date value is not null.
 */
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
