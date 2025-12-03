package com.glassgo.platform.analytics.domain.model.queries;

/**
 * Query representing the intent to retrieve a Record aggregate by its associated order identifier.
 * <p>
 * This query supports read operations in the CQRS architecture, allowing clients to fetch records
 * based on external order IDs without coupling to internal aggregate identifiers.
 * </p>
 *
 * @param orderId The unique identifier of the order associated with the record.
 */
public record GetReportByOrderIdQuery(String orderId) { // Renamed from GetReportByOrderIdQuery to GetRecordByOrderIdQuery
    public GetReportByOrderIdQuery {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId must not be null or blank");
        }
    }
}
