package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

import java.util.Date;

public record CreateTransactionResource(
        Long subscriptionId,
        Double amount,
        String currency,
        Date paymentDate,
        String paymentMethod,
        String status,
        String externalTransactionId
) {
    public CreateTransactionResource {
        if (subscriptionId == null)
            throw new IllegalArgumentException("subscriptionId cannot be null");
        if (amount == null)
            throw new IllegalArgumentException("amount cannot be null");
        if (currency == null || currency.isBlank())
            throw new IllegalArgumentException("currency cannot be null");
        if (paymentDate == null)
            throw new IllegalArgumentException("paymentDate cannot be null");
        if (paymentMethod == null || paymentMethod.isBlank())
            throw new IllegalArgumentException("paymentMethod cannot be null");
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("status cannot be null");
        if (externalTransactionId == null || externalTransactionId.isBlank())
            throw new IllegalArgumentException("externalTransactionId cannot be null");
    }
}
