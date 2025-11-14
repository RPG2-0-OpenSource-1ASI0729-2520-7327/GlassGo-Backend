package com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreatePaymentGatewayCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdatePaymentGatewayCommand;
import com.glassgo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Entity
@Getter
public class PaymentGateway extends AuditableAbstractAggregateRoot<PaymentGateway> {
    @Column(name = "name")
    private String name;

    @Column(name = "api_url")
    private String apiUrl;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "secret_key")
    private String secretKey;

    public PaymentGateway() {
        this.name = Strings.EMPTY;
        this.apiUrl = Strings.EMPTY;
        this.clientId = Strings.EMPTY;
        this.secretKey = Strings.EMPTY;
    }

    public PaymentGateway(CreatePaymentGatewayCommand command) {
        this.name = command.name();
        this.apiUrl = command.apiUrl();
        this.clientId = command.clientId();
        this.secretKey = command.secretKey();
    }

    public PaymentGateway updateInformation(UpdatePaymentGatewayCommand command) {
        this.name = command.name();
        this.apiUrl = command.apiUrl();
        this.clientId = command.clientId();
        this.secretKey = command.secretKey();
        return this;
    }
}
