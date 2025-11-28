package com.glassgo.platform.paymentsandsubscriptions.application.internal.queryservices;

import com.glassgo.platform.paymentsandsubscriptions.domain.model.aggregates.Subscription;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.queries.*;
import com.glassgo.platform.paymentsandsubscriptions.domain.model.services.SubscriptionQueryService;
import com.glassgo.platform.paymentsandsubscriptions.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link SubscriptionQueryService} interface.
 * Handles queries related to {@link Subscription} entities.
 */
@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Handles the {@link GetSubscriptionByIdQuery} to retrieve a {@link Subscription} by its ID.
     * @param query - the query containing the ID of the {@link Subscription} to retrieve
     * @return - an {@link Optional} containing the {@link Subscription} if found, or empty if not found
     */
    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return subscriptionRepository.findById(query.subscriptionId());
    }

    /**
     * Handles the {@link GetAllSubscriptionsQuery} to retrieve all {@link Subscription} entities.
     * @param query - the query to retrieve all {@link Subscription} entities
     * @return - a list of all {@link Subscription} entities
     */
    @Override
    public List<Subscription> handle(GetAllSubscriptionsQuery query) {
        return subscriptionRepository.findAll();
    }

    /**
     * Handles the {@link GetAllSubscriptionsByPlanIdQuery} to retrieve all {@link Subscription} entities by Plan ID.
     * @param query - the query containing the Plan ID to filter {@link Subscription} entities
     * @return - a list of {@link Subscription} entities associated with the specified Plan ID
     */
    @Override
    public List<Subscription> handle(GetAllSubscriptionsByPlanIdQuery query) {
        return subscriptionRepository.findByPlanId(query.planId());
    }

    /**
     * Handles the {@link GetAllSubscriptionsByStatusQuery} to retrieve all {@link Subscription} entities by status.
     * @param query - the query containing the status to filter {@link Subscription} entities
     * @return - a list of {@link Subscription} entities with the specified status
     */
    @Override
    public List<Subscription> handle(GetAllSubscriptionsByStatusQuery query) {
        return subscriptionRepository.findByStatus(query.status());
    }

    /**
     * Handles the {@link GetAllSubscriptionsByUserIdQuery} to retrieve all {@link Subscription} entities by User ID.
     * @param query - the query containing the User ID to filter {@link Subscription} entities
     * @return - a list of {@link Subscription} entities associated with the specified User ID
     */
    @Override
    public List<Subscription> handle(GetAllSubscriptionsByUserIdQuery query) {
        return subscriptionRepository.findByUserId(query.userId());
    }
}
