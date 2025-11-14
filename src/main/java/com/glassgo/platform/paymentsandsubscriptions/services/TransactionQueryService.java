package com.glassgo.platform.paymentsandsubscriptions.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Transaction;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TransactionQueryService {
    Optional<Transaction> handle(GetTransactionByIdQuery query);
    List<Transaction> handle(GetAllTransactionsQuery query);
    List<Transaction> handle(GetAllTransactionsByCurrencyQuery query);
    List<Transaction> handle(GetAllTransactionsByPaymentMethodQuery query);
    List<Transaction> handle(GetAllTransactionsByStatusQuery query);
}
