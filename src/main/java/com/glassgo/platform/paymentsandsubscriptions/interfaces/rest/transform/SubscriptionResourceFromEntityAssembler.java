package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.transform;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Subscription;
import com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources.SubscriptionResource;

/**
 * Assembler to convert {@link Subscription} entity to {@link SubscriptionResource}.
 */
public class SubscriptionResourceFromEntityAssembler {
    /**
     * Converts a {@link Subscription} entity to a {@link SubscriptionResource}.
     * @param entity - The subscription entity.
     * @return - The subscription resource.
     */
    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        return new SubscriptionResource(
                entity.getId(),
                entity.getUserId(),
                entity.getPlanId(),
                entity.getStatus(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getAutoRenew()
        );
    }
}
