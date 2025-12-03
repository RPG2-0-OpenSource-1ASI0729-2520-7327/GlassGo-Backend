package com.glassgo.platform.payments.interfaces.rest.resources;

/**
 * Representation of a Payment Gateway exposed by the REST API.
 * <p>
 * Contains the connection/credential information for a configured payment gateway.
 * Handle sensitive fields (like secretKey) with care and avoid logging them.
 *
 * @param id        the payment gateway ID
 * @param name      the name of the payment gateway
 * @param apiUrl    the API URL
 * @param clientId  the client ID
 * @param secretKey the secret key
 */
public record PaymentGatewayResource(
        Long id,
        String name,
        String apiUrl,
        String clientId,
        String secretKey
) {
}
