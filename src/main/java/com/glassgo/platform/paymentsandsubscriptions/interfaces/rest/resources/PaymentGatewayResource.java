package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

/**
 * Representation of a Payment Gateway exposed by the REST API.
 * <p>
 * Contains the connection/credential information for a configured payment gateway.
 * Handle sensitive fields (like secretKey) with care and avoid logging them.
 */
public record PaymentGatewayResource(
        Long id,
        String name,
        String apiUrl,
        String clientId,
        String secretKey
) {
}
