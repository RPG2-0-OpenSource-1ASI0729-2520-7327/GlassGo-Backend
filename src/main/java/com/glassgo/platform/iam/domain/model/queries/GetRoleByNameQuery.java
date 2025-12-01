package com.glassgo.platform.iam.domain.model.queries;

import com.glassgo.platform.iam.domain.model.valueobjects.Roles;

/**
 * Query to retrieve a specific role by its name in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This record encapsulates the criteria to fetch a single role entity based on its enumerated name.
 * It is used by the application service for operations requiring role lookup, such as assigning
 * roles to users or checking permissions.
 * </p>
 *
 * @param name the enumerated name of the role to retrieve
 * @see Roles
 */
public record GetRoleByNameQuery(Roles name) {
}
