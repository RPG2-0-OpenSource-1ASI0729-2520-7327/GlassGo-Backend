package com.glassgo.platform.paymentsandsubscriptions.application.internal.queryservices;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.SubscriptionPlan;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.GetAllSubscriptionPlansQuery;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.GetSubscriptionPlanByIdQuery;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.services.SubscriptionPlanQueryService;
import com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link SubscriptionPlanQueryService} interface.
 * Handles queries related to {@link SubscriptionPlan} entities.
 */
@Service
public class SubscriptionPlanQueryServiceImpl implements SubscriptionPlanQueryService {
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    public SubscriptionPlanQueryServiceImpl(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    /**
     * Handles the {@link GetSubscriptionPlanByIdQuery} to retrieve a {@link SubscriptionPlan} by its ID.
     * @param query - the query containing the ID of the {@link SubscriptionPlan} to retrieve
     * @return - an {@link Optional} containing the {@link SubscriptionPlan} if found, or empty if not found
     */
    @Override
    public Optional<SubscriptionPlan> handle(GetSubscriptionPlanByIdQuery query) {
        return subscriptionPlanRepository.findById(query.subscriptionPlanId());
    }

    /**
     * Handles the {@link GetAllSubscriptionPlansQuery} to retrieve all {@link SubscriptionPlan} entities.
     * @param query - the query to retrieve all {@link SubscriptionPlan} entities
     * @return - a list of all {@link SubscriptionPlan} entities
     */
    @Override
    public List<SubscriptionPlan> handle(GetAllSubscriptionPlansQuery query) {
        return subscriptionPlanRepository.findAll();
    }
}
