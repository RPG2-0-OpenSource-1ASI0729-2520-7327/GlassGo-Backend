package com.glassgo.platform.paymentsandsubscriptions.domain.model.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.PaymentGateway;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreatePaymentGatewayCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.DeletePaymentGatewayCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdatePaymentGatewayCommand;

import java.util.Optional;

public interface PaymentGatewayCommandService {
    Long handle(CreatePaymentGatewayCommand command);
    Optional<PaymentGateway> handle(UpdatePaymentGatewayCommand command);
    void handle(DeletePaymentGatewayCommand command);
}
