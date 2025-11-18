package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.transform;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdateTransactionCommand;
import com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources.TransactionResource;

/**
 * Assembler to convert {@link TransactionResource} to {@link UpdateTransactionCommand}.
 */
public class UpdateTransactionCommandFromResourceAssembler {
    /**
     * Converts a {@link TransactionResource} to a {@link UpdateTransactionCommand}.
     * @param resource - The transaction resource.
     * @return - The update transaction command.
     */
    public static UpdateTransactionCommand toCommandFromResource(TransactionResource resource) {
        return new UpdateTransactionCommand(
                resource.id(),
                resource.amount(),
                resource.currency(),
                resource.paymentMethod(),
                resource.status()
        );
    }
}
