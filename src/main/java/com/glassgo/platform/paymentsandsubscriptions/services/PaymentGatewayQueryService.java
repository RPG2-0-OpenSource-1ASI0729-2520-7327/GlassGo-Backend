package com.glassgo.platform.paymentsandsubscriptions.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.PaymentGateway;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.GetAllPaymentGatewaysQuery;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.GetPaymentGatewayByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentGatewayQueryService {
    Optional<PaymentGateway> handle(GetPaymentGatewayByIdQuery query);
    List<PaymentGateway> handle(GetAllPaymentGatewaysQuery query);
}
