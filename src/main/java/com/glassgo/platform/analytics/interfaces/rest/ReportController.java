package com.glassgo.platform.analytics.interfaces.rest;

import com.glassgo.platform.analytics.domain.model.queries.GetReportByIdQuery;
import com.glassgo.platform.analytics.domain.model.queries.GetReportByOrderIdQuery; // Updated import
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

/**
 * REST controller for managing Order Tracking Reports.
 * <p>
 * This controller provides endpoints for creating and retrieving reports that
 * track the lifecycle of orders through various timestamps.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Order Tracking Reports", description = "Endpoints for managing Order Tracking Reports")
public class ReportController {

    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;

    /**
     * Constructs the controller with the required command and query services.
     *
     * @param reportCommandService Service for handling report creation commands.
     * @param reportQueryService   Service for handling report queries.
     */
    public ReportController(ReportCommandService reportCommandService, ReportQueryService reportQueryService) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
    }

    /**
     * Creates a new Order Tracking Report.
     * <p>
     * This endpoint allows for the creation of a report that records key timestamps
     * in an order's lifecycle, such as creation, packaging start, shipping, and reception.
     * </p>
     *
     * @param resource The resource containing the order ID and various timestamps.
     * @return A ResponseEntity with the created report resource.
     */
    @PostMapping
    @Operation(summary = "Create a new Order Tracking Report", description = "Records key timestamps for an order's lifecycle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Report created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
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
     * Retrieves an Order Tracking Report by its associated order identifier.
     *
     * @param orderId The unique identifier of the order to retrieve the report for.
     * @return A ResponseEntity containing the report resource, or not found if it doesn't exist.
     */
    @GetMapping("/order/{orderId}") // Updated path
    @Operation(summary = "Get Order Tracking Report by Order ID", description = "Retrieves a report by the associated order ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report found"),
            @ApiResponse(responseCode = "404", description = "Report not found")
    })
    public ResponseEntity<ReportResource> getReportByOrderId(@PathVariable String orderId) { // Updated method name and parameter
        var getReportByOrderIdQuery = new GetReportByOrderIdQuery(orderId); // Updated query
        var report = reportQueryService.handle(getReportByOrderIdQuery);
        if (report.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var reportResource = ReportFromEntityAssembler.toResourceFromEntity(report.get());
        return ResponseEntity.ok(reportResource);
    }

    /**
     * Retrieves an Order Tracking Report by its unique report identifier.
     *
     * @param id The unique identifier of the report to retrieve.
     * @return A ResponseEntity containing a list of report resources (typically one), or not found.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get Order Tracking Report by Report ID", description = "Retrieves a report by its unique report ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reports found"),
            @ApiResponse(responseCode = "404", description = "No reports found")
    })
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
