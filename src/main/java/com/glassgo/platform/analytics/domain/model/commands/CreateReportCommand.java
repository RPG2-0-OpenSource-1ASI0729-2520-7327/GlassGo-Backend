package com.glassgo.platform.analytics.domain.model.commands;

/**
 * Command representing the intent to create a new Report aggregate.
 * In the context of Command Query Responsibility Segregation (CQRS), this command
 * encapsulates the data required to initialize a report, ensuring validation
 * and immutability of the creation parameters.
 *
 * @param sourceId the unique identifier of the source associated with the report
 */
public record CreateReportCommand(String sourceId) {
    public CreateReportCommand {
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId must not be null or blank");
        }
    }
}
