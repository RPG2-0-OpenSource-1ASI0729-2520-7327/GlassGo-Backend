package com.glassgo.platform.analytics.domain.model.queries;

/**
 * Query representing the intent to retrieve a Record aggregate by its unique identifier.
 * <p>
 * In the CQRS pattern, this query encapsulates the criteria for fetching a specific record,
 * ensuring that read operations are separated from write operations and promoting scalability.
 * </p>
 *
 * @param id The unique identifier of the record to retrieve.
 */
public record GetReportByIdQuery(Long id) { // Renamed from GetReportByIdQuery to GetRecordByIdQuery
    public GetReportByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id must not be null or less than or equal to zero");
        }
    }
}
