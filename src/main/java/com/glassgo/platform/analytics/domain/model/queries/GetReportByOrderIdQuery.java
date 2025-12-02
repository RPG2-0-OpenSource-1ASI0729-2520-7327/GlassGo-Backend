package com.glassgo.platform.analytics.domain.model.queries;

/**
 * Query representing the intent to retrieve a Report aggregate by its associated order identifier.
 * <p>
 * This query supports read operations in the CQRS architecture, allowing clients to fetch reports
 * based on external order IDs without coupling to internal aggregate identifiers.
 * </p>
 *
 * @param orderId The unique identifier of the order associated with the report.
 */
public record GetReportByOrderIdQuery(String orderId) {
    public GetReportByOrderIdQuery {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId must not be null or blank");
        }
    }
}
