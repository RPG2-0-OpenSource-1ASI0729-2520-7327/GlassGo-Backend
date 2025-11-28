package com.glassgo.platform.paymentsandsubscriptions.application.internal.commandservices;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Subscription;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.DeleteSubscriptionCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdateSubscriptionCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.services.SubscriptionCommandService;
import com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the {@link SubscriptionCommandService} interface.
 * Handles commands related to {@link Subscription} operations such as create, update, and delete.
 */
@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Creates a new {@link Subscription} based on the provided command.
     * @param command - {@link CreateSubscriptionCommand} containing the details for the new {@link Subscription}.
     * @return - The ID of the newly created {@link Subscription}.
     */
    @Override
    public Long handle(CreateSubscriptionCommand command) {
        var subscription = new Subscription(command);
        try {
            subscriptionRepository.save(subscription);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error saving Subscription: %s".formatted(ex.getMessage()));
        }
        return subscription.getId();
    }

    /**
     * Updates an existing {@link Subscription} based on the provided command.
     * @param command - {@link UpdateSubscriptionCommand} containing the updated details for the {@link Subscription}.
     * @return - An {@link Optional} containing the updated {@link Subscription} if the update was successful, or empty if not found.
     */
    @Override
    public Optional<Subscription> handle(UpdateSubscriptionCommand command) {
        if (!subscriptionRepository.existsById(command.subscriptionId()))
            throw new IllegalArgumentException("Subscription with ID %s already exists".formatted(command.subscriptionId()));
        var result = subscriptionRepository.findById(command.subscriptionId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Subscription with ID %s not found".formatted(command.subscriptionId()));
        var subscriptionToUpdate = result.get();
        try {
            var updatedSubscription = subscriptionRepository.save(subscriptionToUpdate.updateInformation(command));
            return Optional.of(updatedSubscription);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error updating Subscription: %s".formatted(ex.getMessage()));
        }
    }

    /**
     * Deletes an existing {@link Subscription} based on the provided command.
     * @param command - {@link DeleteSubscriptionCommand} containing the ID of the {@link Subscription} to be deleted.
     */
    @Override
    public void handle(DeleteSubscriptionCommand command) {
        if (!subscriptionRepository.existsById(command.subscriptionId()))
            throw new IllegalArgumentException("Subscription with ID %s not found".formatted(command.subscriptionId()));
        try {
            subscriptionRepository.deleteById(command.subscriptionId());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error deleting Subscription: %s".formatted(ex.getMessage()));
        }
    }
}
