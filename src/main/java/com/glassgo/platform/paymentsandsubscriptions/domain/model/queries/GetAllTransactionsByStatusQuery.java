package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

/**
 * Query to fetch transactions filtered by status.
 */
public record GetAllTransactionsByStatusQuery(String status) {
    public GetAllTransactionsByStatusQuery {
        if (status == null || status.isEmpty())
            throw new IllegalArgumentException("status cannot be null or empty");
    }
}
