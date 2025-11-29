package com.glassgo.platform.analytics.interfaces.rest;

import com.glassgo.platform.analytics.domain.model.queries.GetReportByIdQuery;
import com.glassgo.platform.analytics.domain.model.queries.GetReportBySourceIdQuery;
import com.glassgo.platform.analytics.domain.services.ReportCommandService;
import com.glassgo.platform.analytics.domain.services.ReportQueryService;
import com.glassgo.platform.analytics.interfaces.rest.resources.CreateReportResource;
import com.glassgo.platform.analytics.interfaces.rest.resources.ReportResource;
import com.glassgo.platform.analytics.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import com.glassgo.platform.analytics.interfaces.rest.transform.ReportFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reports", description = "Report Management Endpoints")
public class ReportController {

    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;

    public ReportController(ReportCommandService reportCommandService, ReportQueryService reportQueryService) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
    }

    /**
     * Creates a new report.
     * @param resource the resource containing the report data
     * @return the created report resource
     */
    @Operation(summary = "Create a new report", description = "Creates a new report with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Report created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<ReportResource> createReport(@RequestBody CreateReportResource resource) {
        var createReportCommand = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);
        var report = reportCommandService.handle(createReportCommand);
        if (report.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var reportResource = ReportFromEntityAssembler.toResourceFromEntity(report.get());
        return new ResponseEntity<>(reportResource, HttpStatus.CREATED);
    }

    /**
     * Gets a report by source ID.
     * @param sourceId the source ID
     * @return the report resource
     */
    @Operation(summary = "Get report by source ID", description = "Retrieves a report by its source ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report found"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    @GetMapping("/source/{sourceId}")
    public ResponseEntity<ReportResource> getReportBySourceId(@PathVariable String sourceId) {
        var getReportBySourceIdQuery = new GetReportBySourceIdQuery(sourceId);
        var report = reportQueryService.handle(getReportBySourceIdQuery);
        if (report.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var reportResource = ReportFromEntityAssembler.toResourceFromEntity(report.get());
        return ResponseEntity.ok(reportResource);
    }

    /**
     * Gets all reports by ID (note: this seems to return a list, consider renaming the query).
     * @param id the report ID
     * @return the list of report resources
     */
    @Operation(summary = "Get reports by ID", description = "Retrieves reports by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reports found"),
            @ApiResponse(responseCode = "404", description = "No reports found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<ReportResource>> getReportById(@PathVariable Long id) {
        var getReportByIdQuery = new GetReportByIdQuery(id);
        var reports = reportQueryService.handle(getReportByIdQuery);
        if (reports.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var reportResources = reports.stream()
                .map(ReportFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(reportResources);
    }
}
