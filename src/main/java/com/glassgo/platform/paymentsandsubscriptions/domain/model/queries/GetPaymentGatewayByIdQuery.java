package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

public record GetPaymentGatewayByIdQuery(Long paymentGatewayId) {
    public GetPaymentGatewayByIdQuery {
        if (paymentGatewayId == null)
            throw new IllegalArgumentException("paymentGatewayId cannot be null");
    }
}
