package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.transform;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Transaction;
import com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources.TransactionResource;

/**
 * Assembler to convert {@link Transaction} entity to {@link TransactionResource}.
 */
public class TransactionResourceFromEntityAssembler {
    /**
     * Converts a {@link Transaction} entity to a {@link TransactionResource}.
     * @param entity - The transaction entity.
     * @return - The transaction resource.
     */
    public static TransactionResource toResourceFromEntity(Transaction entity) {
        return new TransactionResource(
                entity.getId(),
                entity.getSubscriptionId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getPaymentDate(),
                entity.getPaymentMethod(),
                entity.getStatus(),
                entity.getExternalTransactionId()
        );
    }
}
