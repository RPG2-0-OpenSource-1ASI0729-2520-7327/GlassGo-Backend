package com.glassgo.platform.paymentsandsubscriptions.domain.model.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Transaction;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

/**
 * Query service for Transaction read operations.
 */
public interface TransactionQueryService {
    Optional<Transaction> handle(GetTransactionByIdQuery query);
    List<Transaction> handle(GetAllTransactionsQuery query);
    List<Transaction> handle(GetAllTransactionsByStatusQuery query);
    List<Transaction> handle(GetAllTransactionsByCurrencyQuery query);
    List<Transaction> handle(GetAllTransactionsByPaymentMethodQuery query);
}
