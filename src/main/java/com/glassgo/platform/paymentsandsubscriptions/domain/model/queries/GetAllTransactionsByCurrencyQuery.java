package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

/**
 * Query to fetch transactions filtered by currency.
 */
public record GetAllTransactionsByCurrencyQuery(String currency) {
    public GetAllTransactionsByCurrencyQuery {
        if (currency == null || currency.isEmpty())
            throw new IllegalArgumentException("currency cannot be null or empty");
    }
}
