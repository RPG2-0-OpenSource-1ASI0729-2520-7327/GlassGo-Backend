package com.glassgo.platform.analytics.domain.services;

import com.glassgo.platform.analytics.domain.model.aggregates.Record; // Added import for Record
import com.glassgo.platform.analytics.domain.model.queries.GetReportByIdQuery;
import com.glassgo.platform.analytics.domain.model.queries.GetReportByOrderIdQuery; // Updated import

import java.util.List;
import java.util.Optional;

/**
 * Domain service interface for handling query operations on Record aggregates.
 * <p>
 * This service provides read-only access to records, supporting various query criteria
 * to retrieve record data without modifying the domain state. It promotes separation
 * of concerns in CQRS by isolating query logic from command logic.
 * </p>
 */
public interface ReportQueryService { // Renamed from ReportQueryService to RecordQueryService
    /**
     * Handles a query to retrieve records by their unique identifier.
     * Note: This method returns a list, which may indicate multiple records or a design choice;
     * typically, ID-based queries return a single entity.
     *
     * @param query The query containing the ID criteria.
     * @return A list of records matching the query criteria.
     */
    List<Record> handle(GetReportByIdQuery query); // Changed from Report to Record

    /**
     * Handles a query to retrieve a record by its order identifier.
     * Returns an Optional to handle cases where no record is found for the given order ID.
     *
     * @param query The query containing the order ID criteria.
     * @return An {@link Optional} containing the record if found, or empty otherwise.
     */
    Optional<Record> handle(GetReportByOrderIdQuery query); // Changed from Report to Record
}
