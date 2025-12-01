package com.glassgo.platform.iam.domain.model.services;

import com.glassgo.platform.iam.domain.model.entities.Role;
import com.glassgo.platform.iam.domain.model.queries.GetAllRolesQuery;
import com.glassgo.platform.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Domain Service for handling role-related queries in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This interface defines the contract for executing queries that retrieve role data without modifying
 * the domain state. It provides read-only access to role information, supporting operations like
 * role listing and lookup for authorization and user management purposes.
 * </p>
 */
public interface RoleQueryService {
    /**
     * Handles the query to retrieve all available roles in the system.
     * <p>
     * This method processes the {@link GetAllRolesQuery} to fetch a complete list of roles,
     * which can be used for administrative interfaces, role assignment UIs, or validation logic.
     * </p>
     *
     * @param query the {@link GetAllRolesQuery} specifying the retrieval criteria
     * @return a list of {@link Role} entities representing all roles in the system
     */
    List<Role> handle(GetAllRolesQuery query);

    /**
     * Handles the query to retrieve a specific role by its name.
     * <p>
     * This method processes the {@link GetRoleByNameQuery} to find a role matching the given
     * enumerated name. It returns an Optional to handle cases where the role does not exist.
     * </p>
     *
     * @param query the {@link GetRoleByNameQuery} containing the role name to search for
     * @return an {@link Optional} containing the {@link Role} entity if found, or empty otherwise
     */
    Optional<Role> handle(GetRoleByNameQuery query);
}
