package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources;

import java.util.Date;

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
