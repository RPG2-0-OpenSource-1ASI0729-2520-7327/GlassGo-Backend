package com.glassgo.platform.analytics.application.internal.queryservices;

import com.glassgo.platform.analytics.domain.model.aggregates.Record; // Changed from Report to Record
import com.glassgo.platform.analytics.domain.model.queries.GetReportByIdQuery;
import com.glassgo.platform.analytics.domain.model.queries.GetReportByOrderIdQuery; // Updated import
import com.glassgo.platform.analytics.domain.services.ReportQueryService;
import com.glassgo.platform.analytics.infrastructure.persistence.jpa.ReportRepository; // This will need to be updated later
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Application service implementation for handling report query operations.
 * <p>
 * This service coordinates read operations on reports, delegating to the repository
 * for data retrieval while maintaining separation between application and domain layers.
 * It implements the domain's {@link ReportQueryService} interface to support CQRS read models.
 * </p>
 */
@Service
public class ReportQueryServiceImpl implements ReportQueryService {

    private final ReportRepository reportRepository; // This will need to be updated later

    /**
     * Constructs the query service with the required repository dependency.
     *
     * @param reportRepository The repository for retrieving reports.
     */
    public ReportQueryServiceImpl(ReportRepository reportRepository) { // This will need to be updated later
        this.reportRepository = reportRepository;
    }

    /**
     * Handles the {@link GetReportByIdQuery} to retrieve a report by its unique identifier.
     *
     * @param query The query containing the report ID.
     * @return A list containing the record if found, or an empty list otherwise.
     */
    @Override
    public List<Record> handle(GetReportByIdQuery query) { // Changed from Report to Record
        // Return a list containing the record if found, or empty list if not found
        return reportRepository.findById(query.id())
                .map(List::of)
                .orElse(List.of());
    }

    /**
     * Handles the {@link GetReportByOrderIdQuery} to retrieve a report by its associated order identifier.
     *
     * @param query The query containing the order ID.
     * @return An {@link Optional} containing the record if found, or empty otherwise.
     */
    @Override
    public Optional<Record> handle(GetReportByOrderIdQuery query) { // Changed from Report to Record
        return reportRepository.findByOrderId(query.orderId()); // Updated method call
    }
}
