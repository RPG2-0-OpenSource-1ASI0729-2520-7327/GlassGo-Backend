package com.glassgo.platform.planning.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.planning.domain.model.aggregates.Order;
import com.glassgo.platform.planning.domain.model.aggregates.OrderRepository;

/**
 * JPA repository interface for Order aggregates.
 * Extends the domain repository to provide JPA-specific data access.
 */
public interface OrderJpaRepository extends OrderRepository {
}
