package com.glassgo.platform.analytics.interfaces.rest.resources;

/**
 * Resource representation for creating a new Report in the analytics bounded context.
 * This record encapsulates the data required to initiate the creation of a report via REST API,
 * serving as the external contract for report creation requests. It is used by assemblers
 * to transform into domain commands, maintaining separation between interface and domain layers.
 *
 * @param sourceId the unique identifier of the source associated with the report
 */
public record CreateReportResource(
        String sourceId) {}
