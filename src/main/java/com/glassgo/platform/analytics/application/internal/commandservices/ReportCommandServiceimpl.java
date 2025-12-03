package com.glassgo.platform.analytics.application.internal.commandservices;

import com.glassgo.platform.analytics.domain.model.aggregates.Record; // Changed from Report to Record
import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand;
import com.glassgo.platform.analytics.domain.services.ReportCommandService;
import com.glassgo.platform.analytics.infrastructure.persistence.jpa.ReportRepository; // This will need to be updated later
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Application service implementation for handling report creation commands.
 * <p>
 * This service acts as an intermediary between the domain layer and infrastructure,
 * orchestrating the creation of reports while enforcing application-level rules.
 * It implements the domain's {@link ReportCommandService} interface.
 * </p>
 */
@Service
public class ReportCommandServiceimpl implements ReportCommandService {

    private final ReportRepository reportRepository; // This will need to be updated later

    /**
     * Constructs the command service with the required repository dependency.
     *
     * @param reportRepository The repository for persisting reports.
     */
    public ReportCommandServiceimpl(ReportRepository reportRepository) { // This will need to be updated later
        this.reportRepository = reportRepository;
    }

    /**
     * Handles the {@link CreateReportCommand} to create a new order tracking report.
     * <p>
     * This method creates a new {@link Record} entity using the data from the command
     * and persists it to the database.
     * </p>
     *
     * @param command The command containing the data for the new report.
     * @return An {@link Optional} containing the created {@link Record} if successful.
     */
    @Override
    public Optional<Record> handle(CreateReportCommand command) { // Changed from Report to Record
        var record = new Record(command); // Changed from report to record
        var savedRecord = reportRepository.save(record); // Changed from report to record, and repository will need update
        return Optional.of(savedRecord);
    }
}
