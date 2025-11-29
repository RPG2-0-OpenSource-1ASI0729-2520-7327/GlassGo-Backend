package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.commands.CreateTransactionCommand;
import com.glassgo.platform.payments.interfaces.rest.resources.CreateTransactionResource;

/**
 * Assembler to convert {@link CreateTransactionResource} to {@link CreateTransactionCommand}.
 */
public class CreateTransactionCommandFromResourceAssembler {
    /**
     * Converts a {@link CreateTransactionResource} to a {@link CreateTransactionCommand}.
     * @param resource - The create transaction resource.
     * @return - The create transaction command.
     */
    public static CreateTransactionCommand toCommandFromResource(CreateTransactionResource resource) {
        return new CreateTransactionCommand(
                resource.subscriptionId(),
                resource.amount(),
                resource.currency(),
                resource.paymentDate(),
                resource.paymentMethod(),
                resource.status(),
                resource.externalTransactionId()
        );
    }
}
