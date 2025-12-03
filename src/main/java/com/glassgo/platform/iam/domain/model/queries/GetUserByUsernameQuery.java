package com.glassgo.platform.iam.domain.model.queries;

/**
 * Query to retrieve a specific user by their username in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This record encapsulates the criteria to fetch a single user aggregate based on their unique username.
 * It is commonly used during authentication processes or for user lookups in administrative interfaces,
 * providing a way to access user data without exposing internal IDs.
 * </p>
 *
 * @param username the unique username of the user to retrieve
 */
public record GetUserByUsernameQuery(String username) {
}
