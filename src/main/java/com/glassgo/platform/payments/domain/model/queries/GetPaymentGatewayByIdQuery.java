package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to obtain a Payment Gateway by its identifier.
 *
 * @param paymentGatewayId the ID of the payment gateway to retrieve
 */
public record GetPaymentGatewayByIdQuery(Long paymentGatewayId) {
    public GetPaymentGatewayByIdQuery {
        if (paymentGatewayId == null)
            throw new IllegalArgumentException("paymentGatewayId cannot be null");
    }
}
