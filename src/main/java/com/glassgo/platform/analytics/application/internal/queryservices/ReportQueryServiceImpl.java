package com.glassgo.platform.analytics.application.internal.queryservices;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.domain.model.queries.GetReportByIdQuery;
import com.glassgo.platform.analytics.domain.model.queries.GetReportBySourceIdQuery;
import com.glassgo.platform.analytics.domain.services.ReportQueryService;
import com.glassgo.platform.analytics.infrastructure.persistence.jpa.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Application service implementation for handling report query operations.
 * This service coordinates read operations on reports, delegating to the repository
 * for data retrieval while maintaining separation between application and domain layers.
 * It implements the domain's ReportQueryService interface to support CQRS read models.
 */
@Service
public class ReportQueryServiceImpl implements ReportQueryService {

    private final ReportRepository reportRepository;

    /**
     * Constructs the query service with the required repository dependency.
     *
     * @param reportRepository the repository for retrieving reports
     */
    public ReportQueryServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> handle(GetReportByIdQuery query) {
        // Return a list containing the report if found, or empty list if not found
        return reportRepository.findById(query.id())
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public Optional<Report> handle(GetReportBySourceIdQuery query) {
        return reportRepository.findBySourceId(query.sourceId());
    }
}
