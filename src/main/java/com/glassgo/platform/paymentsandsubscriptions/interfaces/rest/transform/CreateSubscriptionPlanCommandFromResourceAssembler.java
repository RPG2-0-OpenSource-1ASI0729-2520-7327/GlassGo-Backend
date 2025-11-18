package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.transform;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreateSubscriptionPlanCommand;
import com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources.SubscriptionPlanResource;

/**
 * Assembler to convert {@link SubscriptionPlanResource} to {@link CreateSubscriptionPlanCommand}.
 */
public class CreateSubscriptionPlanCommandFromResourceAssembler {
    /**
     * Converts a {@link SubscriptionPlanResource} to a {@link CreateSubscriptionPlanCommand}.
     * @param resource - The subscription plan resource.
     * @return - The create subscription plan command.
     */
    public static CreateSubscriptionPlanCommand toCommandFromResource(SubscriptionPlanResource resource) {
        return new CreateSubscriptionPlanCommand(
                resource.name(),
                resource.description(),
                resource.price(),
                resource.durationMonths()
        );
    }
}
