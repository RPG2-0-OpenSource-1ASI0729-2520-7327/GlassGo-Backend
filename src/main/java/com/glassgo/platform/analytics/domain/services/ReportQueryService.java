package com.glassgo.platform.analytics.domain.services;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.domain.model.queries.GetReportByIdQuery;
import com.glassgo.platform.analytics.domain.model.queries.GetReportByOrderIdQuery; // Updated import

import java.util.List;
import java.util.Optional;

/**
 * Domain service interface for handling query operations on Report aggregates.
 * <p>
 * This service provides read-only access to reports, supporting various query criteria
 * to retrieve report data without modifying the domain state. It promotes separation
 * of concerns in CQRS by isolating query logic from command logic.
 * </p>
 */
public interface ReportQueryService {
    /**
     * Handles a query to retrieve reports by their unique identifier.
     * Note: This method returns a list, which may indicate multiple reports or a design choice;
     * typically, ID-based queries return a single entity.
     *
     * @param query The query containing the ID criteria.
     * @return A list of reports matching the query criteria.
     */
    List<Report> handle(GetReportByIdQuery query);

    /**
     * Handles a query to retrieve a report by its order identifier.
     * Returns an Optional to handle cases where no report is found for the given order ID.
     *
     * @param query The query containing the order ID criteria.
     * @return An {@link Optional} containing the report if found, or empty otherwise.
     */
    Optional<Report> handle(GetReportByOrderIdQuery query); // Updated method signature
}
