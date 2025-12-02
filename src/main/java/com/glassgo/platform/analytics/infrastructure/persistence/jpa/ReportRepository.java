package com.glassgo.platform.analytics.infrastructure.persistence.jpa;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for persisting and retrieving Report aggregates.
 * <p>
 * This infrastructure component provides data access abstraction, leveraging JPA
 * for database operations while maintaining domain model integrity. It supports
 * standard CRUD operations and custom queries for reports.
 * </p>
 */
public interface ReportRepository extends JpaRepository<Report, Long> {

    /**
     * Finds a report by its order identifier.
     * <p>
     * This custom query method allows retrieval of reports based on the associated order ID.
     * </p>
     *
     * @param orderId The order identifier to search for.
     * @return An {@link Optional} containing the report if found, or empty otherwise.
     */
    Optional<Report> findByOrderId(String orderId);
}
