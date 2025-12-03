package com.glassgo.platform.iam.domain.model.queries;

/**
 * Query to retrieve all roles in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This record represents a query to fetch a collection of all available roles from the
 * system. It is used by the application service to obtain role data for display or
 * administrative purposes, such as populating UI elements or validating user permissions.
 * </p>
 */
public record GetAllRolesQuery() {
}
