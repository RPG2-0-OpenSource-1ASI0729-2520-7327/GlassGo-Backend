package com.glassgo.platform.payments.interfaces.rest.transform;

import com.glassgo.platform.payments.domain.model.commands.CreateSubscriptionCommand;
import com.glassgo.platform.payments.interfaces.rest.resources.SubscriptionResource;

/**
 * Assembler to convert {@link SubscriptionResource} to {@link CreateSubscriptionCommand}.
 */
public class CreateSubscriptionCommandFromResourceAssembler {
    /**
     * Converts {@link SubscriptionResource} to {@link CreateSubscriptionCommand}.
     * @param resource - The subscription resource.
     * @return - The create subscription command.
     */
    public static CreateSubscriptionCommand toCommandFromResource(SubscriptionResource resource) {
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
