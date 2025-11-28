package com.glassgo.platform.payments.domain.model.services;

import com.glassgo.platform.payments.domain.model.aggregates.SubscriptionPlan;
import com.glassgo.platform.payments.domain.model.commands.CreateSubscriptionPlanCommand;
import com.glassgo.platform.payments.domain.model.commands.DeleteSubscriptionPlanCommand;
import com.glassgo.platform.payments.domain.model.commands.UpdateSubscriptionPlanCommand;

import java.util.Optional;

/**
 * Command service responsible for mutating Subscription Plans.
 */
public interface SubscriptionPlanCommandService {
    Long handle(CreateSubscriptionPlanCommand command);
    Optional<SubscriptionPlan> handle(UpdateSubscriptionPlanCommand command);
    void handle(DeleteSubscriptionPlanCommand command);
}
