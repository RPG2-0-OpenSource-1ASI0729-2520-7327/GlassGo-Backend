package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

public record UpdateTransactionResource(
        Long transactionId,
        Double amount,
        String currency,
        String paymentMethod,
        String status
) {
    public UpdateTransactionResource {
        if (transactionId == null)
            throw new IllegalArgumentException("transactionId cannot be null");
        if (amount == null)
            throw new IllegalArgumentException("amount cannot be null");
        if (currency == null || currency.isBlank())
            throw new IllegalArgumentException("currency cannot be null or blank");
        if (paymentMethod == null || paymentMethod.isBlank())
            throw new IllegalArgumentException("paymentMethod cannot be null or blank");
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("status cannot be null or blank");
    }
}
