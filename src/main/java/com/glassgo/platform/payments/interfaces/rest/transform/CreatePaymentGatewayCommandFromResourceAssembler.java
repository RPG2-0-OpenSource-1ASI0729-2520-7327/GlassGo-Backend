package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.commands.CreatePaymentGatewayCommand;
import com.glassgo.platform.payments.interfaces.rest.resources.PaymentGatewayResource;

/**
 * Assembler to convert {@link PaymentGatewayResource} to {@link CreatePaymentGatewayCommand}.
 */
public class CreatePaymentGatewayCommandFromResourceAssembler {
    /**
     * Converts {@link PaymentGatewayResource} to {@link CreatePaymentGatewayCommand}.
     * @param resource - The payment gateway resource.
     * @return The create payment gateway command.
     */
    public static CreatePaymentGatewayCommand toCommandFromResource(PaymentGatewayResource resource) {
        return new CreatePaymentGatewayCommand(
                resource.name(),
                resource.apiUrl(),
                resource.clientId(),
                resource.secretKey()
        );
    }
}
