package com.glassgo.platform.analytics.application.internal.commandservices;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand;
import com.glassgo.platform.analytics.domain.services.ReportCommandService;
import com.glassgo.platform.analytics.infrastructure.persistence.jpa.ReportRepository;
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

    private final ReportRepository reportRepository;

    /**
     * Constructs the command service with the required repository dependency.
     *
     * @param reportRepository The repository for persisting reports.
     */
    public ReportCommandServiceimpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Handles the {@link CreateReportCommand} to create a new order tracking report.
     * <p>
     * This method creates a new {@link Report} entity using the data from the command
     * and persists it to the database.
     * </p>
     *
     * @param command The command containing the data for the new report.
     * @return An {@link Optional} containing the created {@link Report} if successful.
     */
    @Override
    public Optional<Report> handle(CreateReportCommand command) {
        var report = new Report(command);
        var savedReport = reportRepository.save(report);
        return Optional.of(savedReport);
    }
}
