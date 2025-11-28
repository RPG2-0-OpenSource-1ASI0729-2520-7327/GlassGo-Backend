package com.glassgo.platform.paymentsandsubscriptions.application.internal.commandservices;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.SubscriptionPlan;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreateSubscriptionPlanCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.DeleteSubscriptionPlanCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdateSubscriptionPlanCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.services.SubscriptionPlanCommandService;
import com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the {@link SubscriptionPlanCommandService} interface.
 * Handles commands related to {@link SubscriptionPlan} operations such as create, update, and delete.
 */
@Service
public class SubscriptionPlanCommandServiceImpl implements SubscriptionPlanCommandService {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public SubscriptionPlanCommandServiceImpl(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    /**
     * Creates a new {@link SubscriptionPlan} based on the provided command.
     * @param command - {@link CreateSubscriptionPlanCommand} containing the details for the new {@link SubscriptionPlan}.
     * @return - The ID of the newly created {@link SubscriptionPlan}.
     */
    @Override
    public Long handle(CreateSubscriptionPlanCommand command) {
        var subscriptionPlan = new SubscriptionPlan(command);
        try {
            subscriptionPlanRepository.save(subscriptionPlan);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error saving Subscription Plan: %s".formatted(ex.getMessage()));
        }
        return subscriptionPlan.getId();
    }

    /**
     * Updates an existing {@link SubscriptionPlan} based on the provided command.
     * @param command - {@link UpdateSubscriptionPlanCommand} containing the updated details for the {@link SubscriptionPlan}.
     * @return - An {@link Optional} containing the updated {@link SubscriptionPlan} if the update was successful, or empty if not found.
     */
    @Override
    public Optional<SubscriptionPlan> handle(UpdateSubscriptionPlanCommand command) {
        if (!subscriptionPlanRepository.existsById(command.subscriptionPlanId()))
            throw new IllegalArgumentException("Subscription Plan with ID %s already exists".formatted(command.subscriptionPlanId()));
        var result = subscriptionPlanRepository.findById(command.subscriptionPlanId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Subscription Plan with ID %s not found".formatted(command.subscriptionPlanId()));
        var subscriptionPlanToUpdate = result.get();
        try {
            var updatedSubscriptionPlan = subscriptionPlanRepository.save(subscriptionPlanToUpdate.updateInformation(command));
            return Optional.of(updatedSubscriptionPlan);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error updating Subscription Plan: %s".formatted(ex.getMessage()));
        }
    }

    /**
     * Deletes an existing {@link SubscriptionPlan} based on the provided command.
     * @param command - {@link DeleteSubscriptionPlanCommand} containing the ID of the {@link SubscriptionPlan} to be deleted.
     */
    @Override
    public void handle(DeleteSubscriptionPlanCommand command) {
        if (!subscriptionPlanRepository.existsById(command.subscriptionPlanId()))
            throw new IllegalArgumentException("Subscription Plan with ID %s not found".formatted(command.subscriptionPlanId()));
        try {
            subscriptionPlanRepository.deleteById(command.subscriptionPlanId());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error deleting Subscription Plan: %s".formatted(ex.getMessage()));
        }
    }
}
