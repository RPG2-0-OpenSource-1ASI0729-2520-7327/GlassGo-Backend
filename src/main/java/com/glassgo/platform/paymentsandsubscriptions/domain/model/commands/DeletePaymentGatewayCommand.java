package com.glassgo.platform.paymentsandsubscriptions.domain.model.commands;

/**
 * Command to delete a Payment Gateway.
 */
public record DeletePaymentGatewayCommand(Long paymentGatewayId) {
    public DeletePaymentGatewayCommand {
        if (paymentGatewayId == null)
            throw new IllegalArgumentException("paymentGatewayId cannot be null");
    }
}
