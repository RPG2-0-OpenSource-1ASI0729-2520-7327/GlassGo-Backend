package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to fetch transactions filtered by currency.
 *
 * @param currency the currency to filter transactions by
 */
public record GetAllTransactionsByCurrencyQuery(String currency) {
    public GetAllTransactionsByCurrencyQuery {
        if (currency == null || currency.isEmpty())
            throw new IllegalArgumentException("currency cannot be null or empty");
    }
}
