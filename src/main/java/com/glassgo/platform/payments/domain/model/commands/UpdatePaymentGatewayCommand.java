package com.glassgo.platform.payments.domain.model.commands;

public record UpdatePaymentGatewayCommand(
        Long paymentGatewayId,
        String name,
        String apiUrl,
        String clientId,
        String secretKey
) {
    public UpdatePaymentGatewayCommand {
        if (paymentGatewayId == null)
            throw new IllegalArgumentException("paymentGatewayId cannot be null");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or blank");
        if (apiUrl == null || apiUrl.isBlank())
            throw new IllegalArgumentException("apiUrl cannot be null or blank");
        if (clientId == null || clientId.isBlank())
            throw new IllegalArgumentException("clientId cannot be null or blank");
        if (secretKey == null || secretKey.isBlank())
            throw new IllegalArgumentException("secretKey cannot be null or blank");
    }
}
