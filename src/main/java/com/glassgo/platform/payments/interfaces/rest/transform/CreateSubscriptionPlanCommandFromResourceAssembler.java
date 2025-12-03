package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.commands.CreateSubscriptionPlanCommand;
import com.glassgo.platform.payments.interfaces.rest.resources.CreateSubscriptionPlanResource;

/**
 * Assembler to convert {@link CreateSubscriptionPlanResource} to {@link CreateSubscriptionPlanCommand}.
 */
public class CreateSubscriptionPlanCommandFromResourceAssembler {
    /**
     * Converts a {@link CreateSubscriptionPlanResource} to a {@link CreateSubscriptionPlanCommand}.
     * @param resource - The create subscription plan resource.
     * @return - The create subscription plan command.
     */
    public static CreateSubscriptionPlanCommand toCommandFromResource(CreateSubscriptionPlanResource resource) {
        return new CreateSubscriptionPlanCommand(
                resource.name(),
                resource.description(),
                resource.price(),
                resource.durationMonths()
        );
    }
}
