package com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.transform;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.SubscriptionPlan;
import com.glassgo.platform.paymentsandsubscriptions.interfaces.rest.resources.SubscriptionPlanResource;

/**
 * Assembler to convert {@link SubscriptionPlan} entity to {@link SubscriptionPlanResource}.
 */
public class SubscriptionPlanResourceFromEntityAssembler {
    /**
     * Converts a {@link SubscriptionPlan} entity to a {@link SubscriptionPlanResource}.
     * @param entity - The subscription plan entity.
     * @return - The subscription plan resource.
     */
    public static SubscriptionPlanResource toResourceFromEntity(SubscriptionPlan entity) {
        return new SubscriptionPlanResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getDurationMonths()
        );
    }
}
