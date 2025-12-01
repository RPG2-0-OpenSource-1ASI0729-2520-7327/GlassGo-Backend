package com.glassgo.platform.payments.domain.model.commands;

/**
 * Command to update an existing Transaction.
 *
 * @param transactionId  the ID of the transaction to update
 * @param amount         the new amount
 * @param currency       the new currency
 * @param paymentMethod  the new payment method
 * @param status         the new status
 */
public record UpdateTransactionCommand(
        Long transactionId,
        Double amount,
        String currency,
        String paymentMethod,
        String status
) {
    public UpdateTransactionCommand {
        if (transactionId == null)
            throw new IllegalArgumentException("transactionId cannot be null");
        if (amount == null)
            throw new IllegalArgumentException("amount cannot be null");
        if (currency == null || currency.isBlank())
            throw new IllegalArgumentException("currency cannot be null or blank");
        if (paymentMethod == null || paymentMethod.isBlank())
            throw new IllegalArgumentException("paymentMethod cannot be null or blank");
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("status cannot be null or blank");
    }
}
