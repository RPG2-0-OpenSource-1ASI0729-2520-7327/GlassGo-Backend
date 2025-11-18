package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.transform;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.commands.UpdateSubscriptionCommand;
import com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources.SubscriptionResource;

/**
 * Assembler to convert {@link SubscriptionResource} to {@link UpdateSubscriptionCommand}.
 */
public class UpdateSubscriptionCommandFromResourceAssembler {
    /**
     * Converts a {@link SubscriptionResource} to a {@link UpdateSubscriptionCommand}.
     * @param resource - The subscription resource.
     * @return - The update subscription command.
     */
    public static UpdateSubscriptionCommand toCommandFromResource(SubscriptionResource resource) {
        return new UpdateSubscriptionCommand(
                resource.id(),
                resource.planId(),
                resource.status(),
                resource.autoRenew()
        );
    }
}
