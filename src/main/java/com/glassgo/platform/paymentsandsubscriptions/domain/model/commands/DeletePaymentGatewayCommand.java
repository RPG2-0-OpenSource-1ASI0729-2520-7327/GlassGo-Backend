package com.glassgo.platform.paymentsandsubscriptions.domain.model.commands;

public record DeletePaymentGatewayCommand(Long paymentGatewayId) {
    public DeletePaymentGatewayCommand {
        if (paymentGatewayId == null)
            throw new IllegalArgumentException("paymentGatewayId cannot be null");
    }
}
