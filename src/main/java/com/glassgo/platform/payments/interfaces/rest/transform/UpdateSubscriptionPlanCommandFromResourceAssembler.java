package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.commands.UpdateSubscriptionPlanCommand;
import com.glassgo.platform.payments.interfaces.rest.resources.SubscriptionPlanResource;

/**
 * Assembler to convert {@link SubscriptionPlanResource} to {@link UpdateSubscriptionPlanCommand}.
 */
public class UpdateSubscriptionPlanCommandFromResourceAssembler {
    /**
     * Converts a {@link SubscriptionPlanResource} to a {@link UpdateSubscriptionPlanCommand}.
     * @param resource - The subscription plan resource.
     * @return - The update subscription plan command.
     */
    public static UpdateSubscriptionPlanCommand toCommandFromResource(SubscriptionPlanResource resource) {
        return new UpdateSubscriptionPlanCommand(
                resource.id(),
                resource.name(),
                resource.description(),
                resource.price(),
                resource.durationMonths()
        );
    }
}
