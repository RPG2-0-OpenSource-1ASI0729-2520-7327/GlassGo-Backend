package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

import java.util.Date;

/**
 * Representation of a Transaction returned by the REST API.
 * <p>
 * Use this resource for transaction-related responses. Sensitive information
 * must be handled according to the project's security guidelines.
 */
public record TransactionResource(
        Long id,
        Double amount,
        String currency,
        Date paymentDate,
        String paymentMethod,
        String status,
        String externalTransactionId
) {
}
