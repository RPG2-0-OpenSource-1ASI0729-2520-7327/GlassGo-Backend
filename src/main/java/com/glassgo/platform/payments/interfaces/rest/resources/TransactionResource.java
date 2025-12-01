package com.glassgo.platform.payments.interfaces.rest.resources;

import java.util.Date;

/**
 * Representation of a Transaction returned by the REST API.
 * <p>
 * Use this resource for transaction-related responses. Sensitive information
 * must be handled according to the project's security guidelines.
 *
 * @param id                    the transaction ID
 * @param subscriptionId        the subscription ID
 * @param amount                the transaction amount
 * @param currency              the transaction currency
 * @param paymentDate           the payment date
 * @param paymentMethod         the payment method
 * @param status                the transaction status
 * @param externalTransactionId the external transaction ID
 */
public record TransactionResource(
        Long id,
        Long subscriptionId,
        Double amount,
        String currency,
        Date paymentDate,
        String paymentMethod,
        String status,
        String externalTransactionId
) {
}
