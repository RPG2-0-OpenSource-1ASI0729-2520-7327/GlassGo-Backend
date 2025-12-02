package com.glassgo.platform.analytics.infrastructure.persistence.jpa;

import com.glassgo.platform.analytics.domain.model.aggregates.Record; // Changed from Report to Record
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for persisting and retrieving Record aggregates.
 * <p>
 * This infrastructure component provides data access abstraction, leveraging JPA
 * for database operations while maintaining domain model integrity. It supports
 * standard CRUD operations and custom queries for records.
 * </p>
 */
public interface ReportRepository extends JpaRepository<Record, Long> { // Changed from Report to Record

    /**
     * Finds a record by its order identifier.
     * <p>
     * This custom query method allows retrieval of records based on the associated order ID.
     * </p>
     *
     * @param orderId The order identifier to search for.
     * @return An {@link Optional} containing the record if found, or empty otherwise.
     */
    Optional<Record> findByOrderId(String orderId); // Changed from Report to Record
}
