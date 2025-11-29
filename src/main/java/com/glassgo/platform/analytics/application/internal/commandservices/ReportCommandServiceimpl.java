package com.glassgo.platform.analytics.application.internal.commandservices;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand;
import com.glassgo.platform.analytics.domain.services.ReportCommandService;
import com.glassgo.platform.analytics.infrastructure.persistence.jpa.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportCommandServiceimpl implements ReportCommandService {

    private final ReportRepository reportRepository;

    public ReportCommandServiceimpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Optional<Report> handle(CreateReportCommand command) {
        // Check if a report with the same sourceId already exists
        if (reportRepository.findBySourceId(command.sourceId()).isPresent()) {
            throw new IllegalArgumentException("Report with sourceId " + command.sourceId() + " already exists");
        }

        // Create and save the new report
        var report = new Report(command);
        var savedReport = reportRepository.save(report);
        return Optional.of(savedReport);
    }
}
