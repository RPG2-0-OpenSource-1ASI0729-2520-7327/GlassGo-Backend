package com.glassgo.platform.paymentsandsubscriptions.domain.model.queries;

/**
 * Query to obtain a Transaction by id.
 */
public record GetTransactionByIdQuery(Long transactionId) {
    public GetTransactionByIdQuery {
        if (transactionId == null)
            throw new IllegalArgumentException("transactionId cannot be null");
    }
}
