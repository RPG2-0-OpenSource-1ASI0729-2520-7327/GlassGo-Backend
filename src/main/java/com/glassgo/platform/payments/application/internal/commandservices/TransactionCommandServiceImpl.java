package com.glassgo.platform.payments.application.internal.commandservices;

import com.glassgo.platform.payments.domain.model.aggregates.Transaction;
import com.glassgo.platform.payments.domain.model.commands.CreateTransactionCommand;
import com.glassgo.platform.payments.domain.model.commands.DeleteTransactionCommand;
import com.glassgo.platform.payments.domain.model.commands.UpdateTransactionCommand;
import com.glassgo.platform.payments.domain.model.services.TransactionCommandService;
import com.glassgo.platform.payments.infrastructure.persistence.jpa.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the {@link TransactionCommandService} interface.
 * Handles commands related to {@link Transaction} operations such as create, update, and delete.
 */
@Service
public class TransactionCommandServiceImpl implements TransactionCommandService {
    private final TransactionRepository transactionRepository;

    public TransactionCommandServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Creates a new {@link Transaction} based on the provided command.
     * @param command - {@link CreateTransactionCommand} containing the details for the new {@link Transaction}.
     * @return - The ID of the newly created {@link Transaction}.
     */
    @Override
    public Long handle(CreateTransactionCommand command) {
        var transaction = new Transaction(command);
        try {
            transactionRepository.save(transaction);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error saving Transaction: %s".formatted(ex.getMessage()));
        }
        return transaction.getId();
    }

    /**
     * Updates an existing {@link Transaction} based on the provided command.
     * @param command - {@link UpdateTransactionCommand} containing the updated details for the {@link Transaction}.
     * @return - An {@link Optional} containing the updated {@link Transaction} if the update was successful, or empty if not found.
     */
    @Override
    public Optional<Transaction> handle(UpdateTransactionCommand command) {
        if (!transactionRepository.existsById(command.transactionId()))
            throw new IllegalArgumentException("Transaction with ID %s already exists".formatted(command.transactionId()));
        var result = transactionRepository.findById(command.transactionId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Transaction with ID %s not found".formatted(command.transactionId()));
        var transactionToUpdate = result.get();
        try {
            var updatedTransaction = transactionRepository.save(transactionToUpdate.updateInformation(command));
            return Optional.of(updatedTransaction);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error updating Transaction: %s".formatted(ex.getMessage()));
        }
    }

    /**
     * Deletes an existing {@link Transaction} based on the provided command.
     * @param command - {@link DeleteTransactionCommand} containing the ID of the {@link Transaction} to be deleted.
     */
    @Override
    public void handle(DeleteTransactionCommand command) {
        if (!transactionRepository.existsById(command.transactionId()))
            throw new IllegalArgumentException("Transaction with ID %s not found".formatted(command.transactionId()));
        try {
            transactionRepository.deleteById(command.transactionId());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error deleting Transaction: %s".formatted(ex.getMessage()));
        }
    }
}
