package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

public record PaymentGatewayResource(
        Long id,
        String name,
        String apiUrl,
        String clientId,
        String secretKey
) {
}
