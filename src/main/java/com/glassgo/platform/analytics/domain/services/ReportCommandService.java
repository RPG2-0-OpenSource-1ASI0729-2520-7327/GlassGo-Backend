package com.glassgo.platform.analytics.domain.services;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand;

import java.util.Optional;

/**
 * Domain service interface for handling command operations on Report aggregates.
 * This service encapsulates the business logic for creating and modifying reports,
 * ensuring that all command-side operations adhere to domain invariants and rules.
 * Implementations of this interface orchestrate the creation of new reports based on commands.
 */
public interface ReportCommandService {
    /**
     * Handles the creation of a new Report aggregate based on the provided command.
     * Validates the command and applies business rules to ensure the report is created correctly.
     * Returns an Optional containing the created report if successful, or empty if creation fails.
     *
     * @param command the command containing the data for creating the report
     * @return an Optional of the created Report, or empty if creation was not possible
     */
    Optional<Report> handle(CreateReportCommand command);
}
