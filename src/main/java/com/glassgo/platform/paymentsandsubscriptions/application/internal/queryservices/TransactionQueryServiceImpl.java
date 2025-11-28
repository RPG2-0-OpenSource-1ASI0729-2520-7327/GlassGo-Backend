package com.glassgo.platform.paymentsandsubscriptions.application.internal.queryservices;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Transaction;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.*;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.services.TransactionQueryService;
import com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link TransactionQueryService} interface.
 * Handles queries related to {@link Transaction} entities.
 */
@Service
public class TransactionQueryServiceImpl implements TransactionQueryService {
    private final TransactionRepository transactionRepository;

    public TransactionQueryServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Handles the {@link GetTransactionByIdQuery} to retrieve a {@link Transaction} by its ID.
     * @param query - the query containing the ID of the {@link Transaction} to retrieve
     * @return - an {@link Optional} containing the {@link Transaction} if found, or empty if not found
     */
    @Override
    public Optional<Transaction> handle(GetTransactionByIdQuery query) {
        return transactionRepository.findById(query.transactionId());
    }

    /**
     * Handles the {@link GetAllTransactionsQuery} to retrieve all {@link Transaction} entities.
     * @param query - the query to retrieve all {@link Transaction} entities
     * @return - a list of all {@link Transaction} entities
     */
    @Override
    public List<Transaction> handle(GetAllTransactionsQuery query) {
        return transactionRepository.findAll();
    }

    /**
     * Handles the {@link GetAllTransactionsByStatusQuery} to retrieve all {@link Transaction} entities by status.
     * @param query - the query containing the status to filter {@link Transaction} entities
     * @return - a list of {@link Transaction} entities with the specified status
     */
    @Override
    public List<Transaction> handle(GetAllTransactionsByStatusQuery query) {
        return transactionRepository.findByStatus(query.status());
    }

    /**
     * Handles the {@link GetAllTransactionsByCurrencyQuery} to retrieve all {@link Transaction} entities by currency.
     * @param query - the query containing the currency to filter {@link Transaction} entities
     * @return - a list of {@link Transaction} entities with the specified currency
     */
    @Override
    public List<Transaction> handle(GetAllTransactionsByCurrencyQuery query) {
        return transactionRepository.findByCurrency(query.currency());
    }

    /**
     * Handles the {@link GetAllTransactionsByPaymentMethodQuery} to retrieve all {@link Transaction} entities by payment method.
     * @param query - the query containing the payment method to filter {@link Transaction} entities
     * @return - a list of {@link Transaction} entities with the specified payment method
     */
    @Override
    public List<Transaction> handle(GetAllTransactionsByPaymentMethodQuery query) {
        return transactionRepository.findByPaymentMethod(query.paymentMethod());
    }
}
