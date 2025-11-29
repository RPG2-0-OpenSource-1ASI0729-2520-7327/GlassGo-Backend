package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.commands.CreatePaymentGatewayCommand;
import com.glassgo.platform.payments.interfaces.rest.resources.CreatePaymentGatewayResource;

/**
 * Assembler to convert {@link CreatePaymentGatewayResource} to {@link CreatePaymentGatewayCommand}.
 */
public class CreatePaymentGatewayCommandFromResourceAssembler {
    /**
     * Converts {@link CreatePaymentGatewayResource} to {@link CreatePaymentGatewayCommand}.
     * @param resource - The create payment gateway resource.
     * @return The create payment gateway command.
     */
    public static CreatePaymentGatewayCommand toCommandFromResource(CreatePaymentGatewayResource resource) {
        return new CreatePaymentGatewayCommand(
                resource.name(),
                resource.apiUrl(),
                resource.clientId(),
                resource.secretKey()
        );
    }
}
