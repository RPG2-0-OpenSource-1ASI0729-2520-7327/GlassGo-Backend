package com.glassgo.platform.paymentsandsubscriptions.domain.model.commands;

public record DeleteTransactionCommand(Long transactionId) {
    public DeleteTransactionCommand {
        if (transactionId == null)
            throw new IllegalArgumentException("transactionId cannot be null");
    }
}
