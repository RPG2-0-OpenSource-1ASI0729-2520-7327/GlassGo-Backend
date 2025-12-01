package com.glassgo.platform.analytics.interfaces.rest.resources;

/**
 * Resource representation of a Report aggregate for REST API responses.
 * This record provides a client-friendly view of the report data, exposing only
 * the necessary attributes for external consumption. It is assembled from domain
 * entities by transformers, ensuring that internal domain details remain hidden.
 *
 * @param id the unique identifier of the report
 * @param sourceId the identifier of the source associated with the report
 */
public record ReportResource(Long id, String sourceId) {
}
