package com.glassgo.platform.analytics.domain.model.queries;

/**
 * Query representing the intent to retrieve a Report aggregate by its associated source identifier.
 * This query supports read operations in the CQRS architecture, allowing clients to fetch reports
 * based on external source IDs without coupling to internal aggregate identifiers.
 *
 * @param sourceId the unique identifier of the source associated with the report
 */
public record GetReportBySourceIdQuery(String sourceId) {
    public GetReportBySourceIdQuery {
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId must not be null or blank");
        }
    }
}
