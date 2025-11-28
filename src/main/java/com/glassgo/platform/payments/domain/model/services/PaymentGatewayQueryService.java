package com.glassgo.platform.payments.domain.model.services;

import com.glassgo.platform.payments.domain.model.aggregates.PaymentGateway;
import com.glassgo.platform.payments.domain.model.queries.GetAllPaymentGatewaysQuery;
import com.glassgo.platform.payments.domain.model.queries.GetPaymentGatewayByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Query service for Payment Gateway read operations.
 * <p>
 * Implementations must avoid exposing secrets unintentionally.
 */
public interface PaymentGatewayQueryService {
    Optional<PaymentGateway> handle(GetPaymentGatewayByIdQuery query);
    List<PaymentGateway> handle(GetAllPaymentGatewaysQuery query);
}
