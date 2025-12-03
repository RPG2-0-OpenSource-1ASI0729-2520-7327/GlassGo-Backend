package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.aggregates.PaymentGateway;
import com.glassgo.platform.payments.interfaces.rest.resources.PaymentGatewayResource;


/**
 * Assembler to convert {@link PaymentGateway} entity to {@link PaymentGatewayResource}.
 */
public class PaymentGatewayResourceFromEntityAssembler {
    /**
     * Converts a {@link PaymentGateway} entity to a {@link PaymentGatewayResource}.
     * @param entity - The payment gateway entity.
     * @return - The payment gateway resource.
     */
    public static PaymentGatewayResource toResourceFromEntity(PaymentGateway entity) {
        return new PaymentGatewayResource(
                entity.getId(),
                entity.getName(),
                entity.getApiUrl(),
                entity.getClientId(),
                entity.getSecretKey()
        );
    }
}
