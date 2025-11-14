package com.glassgo.platform.paymentsandsubscriptions.services;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.SubscriptionPlan;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreateSubscriptionPlanCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.DeleteSubscriptionPlanCommand;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdateSubscriptionPlanCommand;

import java.util.Optional;

public interface SubscriptionPlanCommandService {
    Long handle(CreateSubscriptionPlanCommand command);
    Optional<SubscriptionPlan> handle(UpdateSubscriptionPlanCommand command);
    void handle(DeleteSubscriptionPlanCommand command);
}
