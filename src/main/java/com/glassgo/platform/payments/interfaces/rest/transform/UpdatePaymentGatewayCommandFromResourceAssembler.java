package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.commands.UpdatePaymentGatewayCommand;
import com.glassgo.platform.payments.interfaces.rest.resources.PaymentGatewayResource;

/**
 * Assembler to convert {@link PaymentGatewayResource} to {@link UpdatePaymentGatewayCommand}.
 */
public class UpdatePaymentGatewayCommandFromResourceAssembler {
    /**
     * Converts a {@link PaymentGatewayResource} to a {@link UpdatePaymentGatewayCommand}.
     * @param resource - The payment gateway resource.
     * @return - The update payment gateway command.
     */
    public static UpdatePaymentGatewayCommand toCommandFromResource(PaymentGatewayResource resource) {
        return new UpdatePaymentGatewayCommand(
                resource.id(),
                resource.name(),
                resource.apiUrl(),
                resource.clientId(),
                resource.secretKey()
        );
    }
}
