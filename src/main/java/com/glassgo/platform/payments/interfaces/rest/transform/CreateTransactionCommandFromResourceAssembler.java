package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.commands.CreateTransactionCommand;
import com.glassgo.platform.payments.interfaces.rest.resources.TransactionResource;

/**
 * Assembler to convert {@link TransactionResource} to {@link CreateTransactionCommand}.
 */
public class CreateTransactionCommandFromResourceAssembler {
    /**
     * Converts a {@link TransactionResource} to a {@link CreateTransactionCommand}.
     * @param resource - The transaction resource.
     * @return - The create transaction command.
     */
    public static CreateTransactionCommand toCommandFromResource(TransactionResource resource) {
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
