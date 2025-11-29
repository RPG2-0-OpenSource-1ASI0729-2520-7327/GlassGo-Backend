package com.glassgo.platform.analytics.application.internal.queryservices;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.domain.model.queries.GetReportByIdQuery;
import com.glassgo.platform.analytics.domain.model.queries.GetReportBySourceIdQuery;
import com.glassgo.platform.analytics.domain.services.ReportQueryService;
import com.glassgo.platform.analytics.infrastructure.persistence.jpa.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {

    private final ReportRepository reportRepository;

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
