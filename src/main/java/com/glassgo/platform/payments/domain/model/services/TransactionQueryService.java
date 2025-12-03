package com.glassgo.platform.payments.domain.model.services;

import com.glassgo.platform.payments.domain.model.aggregates.Transaction;
import com.glassgo.platform.payments.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Query service for Transaction read operations.
 */
public interface TransactionQueryService {
    /**
     * Handles the query to get a transaction by ID.
     *
     * @param query the query containing the transaction ID
     * @return an Optional containing the transaction if found
     */
    Optional<Transaction> handle(GetTransactionByIdQuery query);

    /**
     * Handles the query to get all transactions.
     *
     * @param query the query to get all transactions
     * @return a list of all transactions
     */
    List<Transaction> handle(GetAllTransactionsQuery query);

    /**
     * Handles the query to get all transactions by status.
     *
     * @param query the query containing the status
     * @return a list of transactions with the specified status
     */
    List<Transaction> handle(GetAllTransactionsByStatusQuery query);

    /**
     * Handles the query to get all transactions by currency.
     *
     * @param query the query containing the currency
     * @return a list of transactions with the specified currency
     */
    List<Transaction> handle(GetAllTransactionsByCurrencyQuery query);

    /**
     * Handles the query to get all transactions by payment method.
     *
     * @param query the query containing the payment method
     * @return a list of transactions with the specified payment method
     */
    List<Transaction> handle(GetAllTransactionsByPaymentMethodQuery query);
}
