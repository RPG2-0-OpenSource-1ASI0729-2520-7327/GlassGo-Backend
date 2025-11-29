package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.commands.CreateSubscriptionCommand;
import com.glassgo.platform.payments.interfaces.rest.resources.CreateSubscriptionResource;

/**
 * Assembler to convert {@link CreateSubscriptionResource} to {@link CreateSubscriptionCommand}.
 */
public class CreateSubscriptionCommandFromResourceAssembler {
    /**
     * Converts {@link CreateSubscriptionResource} to {@link CreateSubscriptionCommand}.
     * @param resource - The create subscription resource.
     * @return - The create subscription command.
     */
    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(
                resource.userId(),
                resource.planId(),
                resource.status(),
                resource.startDate(),
                resource.endDate(),
                resource.autoRenew()
        );
    }
}
