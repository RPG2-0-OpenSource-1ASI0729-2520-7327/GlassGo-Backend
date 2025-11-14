package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

public record GetAllTransactionsByPaymentMethodQuery(String paymentMethod) {
    public GetAllTransactionsByPaymentMethodQuery {
        if (paymentMethod == null || paymentMethod.isEmpty())
            throw new IllegalArgumentException("paymentMethod cannot be null or empty");
    }
}
