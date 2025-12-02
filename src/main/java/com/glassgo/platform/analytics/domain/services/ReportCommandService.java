package com.glassgo.platform.analytics.domain.services;

import com.glassgo.platform.analytics.domain.model.aggregates.Record; // Added import for Record
import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand;

import java.util.Optional;

/**
 * Domain service interface for handling command operations on Record aggregates.
 * <p>
 * This service encapsulates the business logic for creating and modifying records,
 * ensuring that all command-side operations adhere to domain invariants and rules.
 * Implementations of this interface orchestrate the creation of new records based on commands.
 * </p>
 */
public interface ReportCommandService { // Renamed from ReportCommandService to RecordCommandService
    /**
     * Handles the creation of a new Record aggregate based on the provided command.
     * Validates the command and applies business rules to ensure the record is created correctly.
     * Returns an Optional containing the created record if successful, or empty if creation fails.
     *
     * @param command The command containing the data for creating the record.
     * @return An Optional of the created Record, or empty if creation was not possible.
     */
    Optional<Record> handle(CreateReportCommand command); // Changed from Report to Record
}
