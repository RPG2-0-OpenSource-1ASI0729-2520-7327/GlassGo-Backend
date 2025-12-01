package com.glassgo.platform.iam.interfaces.rest.resources;

/**
 * Resource representation of a role in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This record encapsulates the data transferred over the REST API for role information,
 * providing a client-friendly view of role entities. It includes only the essential
 * attributes needed for role identification and display, maintaining separation between
 * domain entities and external representations.
 * </p>
 *
 * @param id the unique identifier of the role
 * @param name the name of the role (e.g., "ROLE_USER", "ROLE_ADMIN")
 */
public record RoleResource(Long id, String name) {
}
