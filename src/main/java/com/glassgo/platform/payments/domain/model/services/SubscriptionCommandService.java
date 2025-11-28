package com.glassgo.platform.payments.domain.model.services;

import com.glassgo.platform.payments.domain.model.aggregates.Subscription;
import com.glassgo.platform.payments.domain.model.commands.CreateSubscriptionCommand;
import com.glassgo.platform.payments.domain.model.commands.DeleteSubscriptionCommand;
import com.glassgo.platform.payments.domain.model.commands.UpdateSubscriptionCommand;

import java.util.Optional;

/**
 * Command service that exposes mutating operations for Subscriptions.
 * <p>
 * Implementations should apply business rules and persist changes. Methods
 * return either the identifier of the created resource, an optional updated
 * aggregate, or void for delete operations.
 */
public interface SubscriptionCommandService {
    Long handle(CreateSubscriptionCommand command);
    Optional<Subscription> handle(UpdateSubscriptionCommand command);
    void handle(DeleteSubscriptionCommand command);
}
