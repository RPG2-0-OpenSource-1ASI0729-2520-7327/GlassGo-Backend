package com.glassgo.platform.paymentsandsubscriptions.domain.model.commands;

import java.util.Date;

/**
 * Command to create a new Transaction in the domain.
 */
public record CreateTransactionCommand(
        Long subscriptionId,
        Double amount,
        String currency,
        Date paymentDate,
        String paymentMethod,
        String status,
        String externalTransactionId
) {
    public CreateTransactionCommand {
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
