package com.glassgo.platform.paymentsandsubscriptions.domain.model.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Subscription;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.DeleteSubscriptionCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdateSubscriptionCommand;

import java.util.Optional;

public interface SubscriptionCommandService {
    Long handle(CreateSubscriptionCommand command);
    Optional<Subscription> handle(UpdateSubscriptionCommand command);
    void handle(DeleteSubscriptionCommand command);
}
