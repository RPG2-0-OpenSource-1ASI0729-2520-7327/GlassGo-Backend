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
    Long handle(CreateTransactionCommand command);
    Optional<Transaction> handle(UpdateTransactionCommand command);
    void handle(DeleteTransactionCommand command);
}
