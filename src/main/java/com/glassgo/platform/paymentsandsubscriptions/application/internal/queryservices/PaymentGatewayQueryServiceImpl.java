package com.glassgo.platform.paymentsandsubscriptions.application.internal.queryservices;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.PaymentGateway;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.GetAllPaymentGatewaysQuery;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.GetPaymentGatewayByIdQuery;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.services.PaymentGatewayQueryService;
import com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories.PaymentGatewayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link PaymentGatewayQueryService} interface.
 * Handles queries related to {@link PaymentGateway} entities.
 */
@Service
public class PaymentGatewayQueryServiceImpl implements PaymentGatewayQueryService {
    private final PaymentGatewayRepository paymentGatewayRepository;

    public PaymentGatewayQueryServiceImpl(PaymentGatewayRepository paymentGatewayRepository) {
        this.paymentGatewayRepository = paymentGatewayRepository;
    }

    /**
     * Handles the {@link GetPaymentGatewayByIdQuery} to retrieve a {@link PaymentGateway} by its ID.
     * @param query - the query containing the ID of the {@link PaymentGateway} to retrieve
     * @return - an {@link Optional} containing the {@link PaymentGateway} if found, or empty if not found
     */
    @Override
    public Optional<PaymentGateway> handle(GetPaymentGatewayByIdQuery query) {
        return paymentGatewayRepository.findById(query.paymentGatewayId());
    }

    /**
     * Handles the {@link GetAllPaymentGatewaysQuery} to retrieve all {@link PaymentGateway} entities.
     * @param query - the query to retrieve all {@link PaymentGateway} entities
     * @return - a list of all {@link PaymentGateway} entities
     */
    @Override
    public List<PaymentGateway> handle(GetAllPaymentGatewaysQuery query) {
        return paymentGatewayRepository.findAll();
    }
}
