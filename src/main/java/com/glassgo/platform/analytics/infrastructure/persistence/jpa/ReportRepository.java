package com.glassgo.platform.analytics.infrastructure.persistence.jpa;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for persisting and retrieving Report aggregates.
 * This infrastructure component provides data access abstraction, leveraging JPA
 * for database operations while maintaining domain model integrity. It supports
 * standard CRUD operations and custom queries for reports.
 */
public interface ReportRepository extends JpaRepository<Report, Long> {

    /**
     * Finds a report by its source identifier.
     * This custom query method allows retrieval of reports based on external source IDs,
     * facilitating integration with other bounded contexts.
     *
     * @param sourceId the source identifier to search for
     * @return an Optional containing the report if found, or empty otherwise
     */
    Optional<Report> findBySourceId(String sourceId);
}
