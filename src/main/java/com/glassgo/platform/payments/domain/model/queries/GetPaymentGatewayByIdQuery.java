package com.glassgo.platform.payments.domain.model.queries;

public record GetPaymentGatewayByIdQuery(Long paymentGatewayId) {
    public GetPaymentGatewayByIdQuery {
        if (paymentGatewayId == null)
            throw new IllegalArgumentException("paymentGatewayId cannot be null");
    }
}
