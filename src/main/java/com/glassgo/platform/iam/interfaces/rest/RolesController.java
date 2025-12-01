package com.glassgo.platform.iam.interfaces.rest;

import com.glassgo.platform.iam.domain.model.queries.GetAllRolesQuery;
import com.glassgo.platform.iam.domain.model.services.RoleQueryService;
import com.glassgo.platform.iam.interfaces.rest.resources.RoleResource;
import com.glassgo.platform.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing role resources in the IAM bounded context.
 * This interface layer component provides endpoints for retrieving role information,
 * supporting queries for role collections. It assembles domain value objects into
 * resource representations, maintaining separation between domain and interface layers
 * in accordance with DDD principles.
 */
@RestController
@RequestMapping(value = "/ap/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Available Role Endpoints")
public class RolesController {
    private final RoleQueryService roleQueryService;

    /**
     * Constructs the roles controller with the required query service.
     *
     * @param roleQueryService the service handling role queries
     */
    public RolesController(RoleQueryService roleQueryService) {
        this.roleQueryService = roleQueryService;
    }

    /**
     * Get all roles
     * @return List of role resources
     * @see RoleResource
     */
    @GetMapping
    @Operation(summary = "Get all roles", description = "Get all the roles available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<List<RoleResource>> getAllRoles() {
        var getAllRolesQuery = new GetAllRolesQuery();
        var roles = roleQueryService.handle(getAllRolesQuery);
        var roleResources = roles.stream().map(RoleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(roleResources);
    }
}
