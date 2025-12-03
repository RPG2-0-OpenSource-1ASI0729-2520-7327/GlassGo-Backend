package com.glassgo.platform.planning.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DeliveryInfo represents the value object for delivery information in the domain.
 * It is used to store and validate the details of a delivery, such as the date, time,
 * address, and any special instructions.
 *
 * This class is immutable and fully encapsulated. It ensures that all necessary
 * information for a delivery is provided and valid.
 */
@Embeddable
public record DeliveryInfo(
    @Column(name = "delivery_date") LocalDate deliveryDate,
    @Column(name = "delivery_time") LocalTime deliveryTime,
    @Column(name = "delivery_address") String address,
    @Column(name = "delivery_instructions", length = 500) String instructions
) {
    public DeliveryInfo {
        if (deliveryDate == null) {
            throw new IllegalArgumentException("Delivery date cannot be null");
        }
        if (deliveryTime == null) {
            throw new IllegalArgumentException("Delivery time cannot be null");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
    }

    public DeliveryInfo() {
        this(LocalDate.now(), LocalTime.now(), "", "");
    }
}
