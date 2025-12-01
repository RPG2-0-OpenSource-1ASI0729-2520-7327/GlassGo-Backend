package com.glassgo.platform.iam.domain.model.queries;

/**
 * Query to retrieve all users in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This record represents a query to fetch a collection of all user aggregates from the
 * system. It is used by the application service for administrative tasks, such as user
 * management dashboards or reporting, ensuring that sensitive data is handled appropriately.
 * </p>
 */
public record GetAllUsersQuery() {
}
