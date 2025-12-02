package com.glassgo.platform.analytics.interfaces.rest;

import com.glassgo.platform.analytics.domain.model.aggregates.Record; // Added import for Record
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
 * REST controller for managing Order Tracking Records.
 * <p>
 * This controller provides endpoints for creating and retrieving records that
 * track the lifecycle of orders through various timestamps.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/analytics/records", produces = MediaType.APPLICATION_JSON_VALUE) // Modified RequestMapping
@Tag(name = "Analytics", description = "Analytics Endpoints for Order Tracking Records") // Updated Tag
public class ReportController {

    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;

    /**
     * Constructs the controller with the required command and query services.
     *
     * @param reportCommandService Service for handling record creation commands.
     * @param reportQueryService   Service for handling record queries.
     */
    public ReportController(ReportCommandService reportCommandService, ReportQueryService reportQueryService) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
    }

    /**
     * Creates a new Order Tracking Record.
     * <p>
     * This endpoint allows for the creation of a record that records key timestamps
     * in an order's lifecycle, such as creation, packaging start, shipping, and reception.
     * </p>
     *
     * @param resource The resource containing the order ID and various timestamps.
     * @return A ResponseEntity with the created record resource.
     */
    @PostMapping
    @Operation(summary = "Create a new Order Tracking Record", description = "Records key timestamps for an order's lifecycle.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Record created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ReportResource> createRecord(@RequestBody CreateReportResource resource) { // Renamed method
        var createReportCommand = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);
        var record = reportCommandService.handle(createReportCommand); // Changed from report to record
        if (record.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var recordResource = ReportFromEntityAssembler.toResourceFromEntity(record.get()); // Changed from report to record
        return new ResponseEntity<>(recordResource, HttpStatus.CREATED);
    }

    /**
     * Retrieves an Order Tracking Record by its associated order identifier.
     *
     * @param orderId The unique identifier of the order to retrieve the record for.
     * @return A ResponseEntity containing the record resource, or not found if it doesn't exist.
     */
    @GetMapping("/order-tracking-record/{orderId}") // Updated path
    @Operation(summary = "Get Order Tracking Record by Order ID", description = "Retrieves a record by the associated order ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Record found"),
            @ApiResponse(responseCode = "404", description = "Record not found")
    })
    public ResponseEntity<ReportResource> getRecordByOrderId(@PathVariable String orderId) { // Renamed method
        var getReportByOrderIdQuery = new GetReportByOrderIdQuery(orderId); // Updated query
        var record = reportQueryService.handle(getReportByOrderIdQuery); // Changed from report to record
        if (record.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var recordResource = ReportFromEntityAssembler.toResourceFromEntity(record.get()); // Changed from report to record
        return ResponseEntity.ok(recordResource);
    }

    /**
     * Retrieves an Order Tracking Record by its unique record identifier.
     *
     * @param id The unique identifier of the record to retrieve.
     * @return A ResponseEntity containing a list of record resources (typically one), or not found.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get Order Tracking Record by Record ID", description = "Retrieves a record by its unique record ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Records found"),
            @ApiResponse(responseCode = "404", description = "No records found")
    })
    public ResponseEntity<List<ReportResource>> getRecordById(@PathVariable Long id) { // Renamed method
        var getReportByIdQuery = new GetReportByIdQuery(id);
        var records = reportQueryService.handle(getReportByIdQuery); // Changed from reports to records
        if (records.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var recordResources = records.stream()
                .map(ReportFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(recordResources);
    }
}
