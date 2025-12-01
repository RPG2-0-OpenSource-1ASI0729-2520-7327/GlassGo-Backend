package com.glassgo.platform.payments.interfaces.rest.resources;

import java.util.Date;

/**
 * Resource used to create a new Transaction via REST API.
 * <p>
 * This record represents the request payload for creating transactions and
 * performs validation in the compact constructor.
 *
 * @param subscriptionId        the subscription ID
 * @param amount                the transaction amount
 * @param currency              the transaction currency
 * @param paymentDate           the payment date
 * @param paymentMethod         the payment method
 * @param status                the transaction status
 * @param externalTransactionId the external transaction ID
 */
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
