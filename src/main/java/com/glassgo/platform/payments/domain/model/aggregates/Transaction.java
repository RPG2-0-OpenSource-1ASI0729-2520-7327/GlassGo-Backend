package com.glassgo.platform.payments.domain.model.aggregates;

import com.glassgo.platform.payments.domain.model.commands.CreateTransactionCommand;
import com.glassgo.platform.payments.domain.model.commands.UpdateTransactionCommand;
import com.glassgo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

/**
 * Aggregate representing a payment Transaction.
 */
@Entity
@Getter
public class Transaction extends AuditableAbstractAggregateRoot<Transaction> {
    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "status")
    private String status;

    @Column(name = "external_transaction_id")
    private String externalTransactionId;

    public Transaction() {
        this.subscriptionId = null;
        this.amount = null;
        this.currency = Strings.EMPTY;
        this.paymentDate = null;
        this.paymentMethod = Strings.EMPTY;
        this.status = Strings.EMPTY;
        this.externalTransactionId = Strings.EMPTY;
    }

    /**
     * Constructs a new Transaction from the given command.
     *
     * @param command the command containing transaction details
     */
    public Transaction(CreateTransactionCommand command) {
        this.subscriptionId = command.subscriptionId();
        this.amount = command.amount();
        this.currency = command.currency();
        this.paymentDate = command.paymentDate();
        this.paymentMethod = command.paymentMethod();
        this.status = command.status();
        this.externalTransactionId = command.externalTransactionId();
    }

    /**
     * Updates the transaction information based on the given command.
     *
     * @param command the command containing updated transaction details
     * @return the updated transaction
     */
    public Transaction updateInformation(UpdateTransactionCommand command) {
        this.amount = command.amount();
        this.currency = command.currency();
        this.paymentMethod = command.paymentMethod();
        this.status = command.status();
        return this;
    }
}
