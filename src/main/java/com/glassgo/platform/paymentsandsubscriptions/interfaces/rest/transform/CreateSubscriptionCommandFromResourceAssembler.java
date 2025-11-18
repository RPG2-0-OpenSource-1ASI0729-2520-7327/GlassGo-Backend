package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.transform;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources.SubscriptionResource;

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
