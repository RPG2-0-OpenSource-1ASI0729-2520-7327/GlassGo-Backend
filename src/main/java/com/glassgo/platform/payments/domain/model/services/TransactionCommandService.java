package com.glassgo.platform.payments.domain.model.services;

import com.glassgo.platform.payments.domain.model.aggregates.Transaction;
import com.glassgo.platform.payments.domain.model.commands.CreateTransactionCommand;
import com.glassgo.platform.payments.domain.model.commands.DeleteTransactionCommand;
import com.glassgo.platform.payments.domain.model.commands.UpdateTransactionCommand;

import java.util.Optional;

/**
 * Command service responsible for mutating Transaction aggregates.
 */
public interface TransactionCommandService {
    /**
     * Handles the creation of a new transaction.
     *
     * @param command the command to create a transaction
     * @return the ID of the created transaction
     */
    Long handle(CreateTransactionCommand command);

    /**
     * Handles the update of an existing transaction.
     *
     * @param command the command to update a transaction
     * @return an Optional containing the updated transaction if found
     */
    Optional<Transaction> handle(UpdateTransactionCommand command);

    /**
     * Handles the deletion of a transaction.
     *
     * @param command the command to delete a transaction
     */
    void handle(DeleteTransactionCommand command);
}
