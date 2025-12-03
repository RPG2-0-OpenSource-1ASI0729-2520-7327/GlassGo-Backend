package com.glassgo.platform.iam.interfaces.rest.resources;

/**
 * Resource representation of an authenticated user for REST API responses in the IAM bounded context.
 * This record encapsulates the essential information returned after successful authentication,
 * including user details and a JWT token for subsequent requests. It serves as the external
 * contract for sign-in operations, hiding internal domain complexities.
 *
 * @param id       the unique identifier of the user
 * @param username the username of the user
 * @param token    the JWT authentication token for authorized access
 */
public record AuthenticatedUserResource(Long id, String username, String token) {

}
