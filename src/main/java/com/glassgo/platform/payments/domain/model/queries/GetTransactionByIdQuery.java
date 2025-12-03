package com.glassgo.platform.payments.domain.model.queries;

/**
 * Query to obtain a Transaction by id.
 *
 * @param transactionId the ID of the transaction to retrieve
 */
public record GetTransactionByIdQuery(Long transactionId) {
    public GetTransactionByIdQuery {
        if (transactionId == null)
            throw new IllegalArgumentException("transactionId cannot be null");
    }
}
