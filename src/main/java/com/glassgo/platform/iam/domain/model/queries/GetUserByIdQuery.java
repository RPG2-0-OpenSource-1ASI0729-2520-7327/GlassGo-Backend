package com.glassgo.platform.iam.domain.model.queries;

/**
 * Query to retrieve a specific user by their ID in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This record encapsulates the criteria to fetch a single user aggregate based on its unique identifier.
 * It is used by the application service for operations such as user profile retrieval, updates, or
 * permission checks, ensuring that the correct user is targeted.
 * </p>
 *
 * @param userId the unique identifier of the user to retrieve
 */
public record GetUserByIdQuery(Long userId) {
}
