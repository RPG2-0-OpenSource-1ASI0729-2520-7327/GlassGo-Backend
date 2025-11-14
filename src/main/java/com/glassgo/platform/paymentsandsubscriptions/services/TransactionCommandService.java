package com.glassgo.platform.paymentsandsubscriptions.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Transaction;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreateTransactionCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.DeleteTransactionCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdateTransactionCommand;

import java.util.Optional;

public interface TransactionCommandService {
    Long handle(CreateTransactionCommand command);
    Optional<Transaction> handle(UpdateTransactionCommand command);
    void handle(DeleteTransactionCommand command);
}
