package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to fetch transactions filtered by status.
 *
 * @param status the status to filter transactions by
 */
public record GetAllTransactionsByStatusQuery(String status) {
    public GetAllTransactionsByStatusQuery {
        if (status == null || status.isEmpty())
            throw new IllegalArgumentException("status cannot be null or empty");
    }
}
