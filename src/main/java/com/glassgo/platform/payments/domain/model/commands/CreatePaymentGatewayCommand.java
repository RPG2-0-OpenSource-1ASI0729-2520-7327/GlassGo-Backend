package com.glassgo.platform.payments.domain.model.commands;

/**
 * Command to create a new Payment Gateway.
 */
public record CreatePaymentGatewayCommand(
        String name,
        String apiUrl,
        String clientId,
        String secretKey
) {
    public CreatePaymentGatewayCommand {
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
