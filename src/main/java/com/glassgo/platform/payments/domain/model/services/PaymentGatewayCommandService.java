package com.glassgo.platform.payments.domain.model.services;

import com.glassgo.platform.payments.domain.model.aggregates.PaymentGateway;
import com.glassgo.platform.payments.domain.model.commands.CreatePaymentGatewayCommand;
import com.glassgo.platform.payments.domain.model.commands.DeletePaymentGatewayCommand;
import com.glassgo.platform.payments.domain.model.commands.UpdatePaymentGatewayCommand;

import java.util.Optional;

/**
 * Command service for mutating Payment Gateway entities.
 */
public interface PaymentGatewayCommandService {
    Long handle(CreatePaymentGatewayCommand command);
    Optional<PaymentGateway> handle(UpdatePaymentGatewayCommand command);
    void handle(DeletePaymentGatewayCommand command);
}
