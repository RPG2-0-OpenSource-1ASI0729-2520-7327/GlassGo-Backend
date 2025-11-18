package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

/**
 * Resource used to create a new Payment Gateway via REST API.
 * <p>
 * Validation is applied in the compact constructor to ensure required fields are provided.
 */
public record CreatePaymentGatewayResource(
        String name,
        String apiUrl,
        String clientId,
        String secretKey
) {
    public CreatePaymentGatewayResource {
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
