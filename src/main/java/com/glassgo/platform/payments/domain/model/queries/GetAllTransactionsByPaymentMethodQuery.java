package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to fetch transactions filtered by payment method.
 *
 * @param paymentMethod the payment method to filter transactions by
 */
public record GetAllTransactionsByPaymentMethodQuery(String paymentMethod) {
    public GetAllTransactionsByPaymentMethodQuery {
        if (paymentMethod == null || paymentMethod.isEmpty())
            throw new IllegalArgumentException("paymentMethod cannot be null or empty");
    }
}
