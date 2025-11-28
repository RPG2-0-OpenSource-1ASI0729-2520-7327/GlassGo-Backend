package com.glassgo.platform.paymentsandsubscriptions.application.internal.commandservices;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.PaymentGateway;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreatePaymentGatewayCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.DeletePaymentGatewayCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdatePaymentGatewayCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.services.PaymentGatewayCommandService;
import com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories.PaymentGatewayRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the {@link PaymentGatewayCommandService} interface.
 * Handles commands related to {@link PaymentGateway} operations such as create, update, and delete.
 */
@Service
public class PaymentGatewayCommandServiceImpl implements PaymentGatewayCommandService {
    private final PaymentGatewayRepository paymentGatewayRepository;

    public PaymentGatewayCommandServiceImpl(PaymentGatewayRepository paymentGatewayRepository) {
        this.paymentGatewayRepository = paymentGatewayRepository;
    }

    /**
     * Creates a new {@link PaymentGateway} based on the provided command.
     * @param command - {@link CreatePaymentGatewayCommand} containing the details for the new {@link PaymentGateway}.
     * @return - The ID of the newly created {@link PaymentGateway}.
     */
    @Override
    public Long handle(CreatePaymentGatewayCommand command) {
        var paymentGateway = new PaymentGateway(command);
        try {
            paymentGatewayRepository.save(paymentGateway);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error saving Payment Gateway: %s".formatted(ex.getMessage()));
        }
        return paymentGateway.getId();
    }

    /**
     * Updates an existing {@link PaymentGateway} based on the provided command.
     * @param command - {@link UpdatePaymentGatewayCommand} containing the updated details for the {@link PaymentGateway}.
     * @return - An {@link Optional} containing the updated {@link PaymentGateway} if the update was successful, or empty if not found.
     */
    @Override
    public Optional<PaymentGateway> handle(UpdatePaymentGatewayCommand command) {
        if (!paymentGatewayRepository.existsById(command.paymentGatewayId()))
            throw new IllegalArgumentException("Payment Gateway with ID %s already exists".formatted(command.paymentGatewayId()));
        var result = paymentGatewayRepository.findById(command.paymentGatewayId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Payment Gateway with ID %s not found".formatted(command.paymentGatewayId()));
        var paymentGatewayToUpdate = result.get();
        try {
            var updatedPaymentGateway = paymentGatewayRepository.save(paymentGatewayToUpdate.updateInformation(command));
            return Optional.of(updatedPaymentGateway);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error updating Payment Gateway: %s".formatted(ex.getMessage()));
        }
    }

    /**
     * Deletes an existing {@link PaymentGateway} based on the provided command.
     * @param command - {@link DeletePaymentGatewayCommand} containing the ID of the {@link PaymentGateway} to be deleted.
     */
    @Override
    public void handle(DeletePaymentGatewayCommand command) {
        if (!paymentGatewayRepository.existsById(command.paymentGatewayId()))
            throw new IllegalArgumentException("Payment Gateway with ID %s not found".formatted(command.paymentGatewayId()));
        try {
            paymentGatewayRepository.deleteById(command.paymentGatewayId());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error deleting Payment Gateway: %s".formatted(ex.getMessage()));
        }
    }
}
