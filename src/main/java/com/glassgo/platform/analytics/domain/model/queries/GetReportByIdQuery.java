package com.glassgo.platform.analytics.domain.model.queries;

/**
 * Query representing the intent to retrieve a Report aggregate by its unique identifier.
 * In the CQRS pattern, this query encapsulates the criteria for fetching a specific report,
 * ensuring that read operations are separated from write operations and promoting scalability.
 *
 * @param id the unique identifier of the report to retrieve
 */
public record GetReportByIdQuery(Long id) {
    public GetReportByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id must not be null or less than or equal to zero");
        }
    }
}
