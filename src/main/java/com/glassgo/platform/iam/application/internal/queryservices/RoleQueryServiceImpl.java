package com.glassgo.platform.iam.application.internal.queryservices;

import com.glassgo.platform.iam.domain.model.entities.Role;
import com.glassgo.platform.iam.domain.model.queries.GetAllRolesQuery;
import com.glassgo.platform.iam.domain.model.queries.GetRoleByNameQuery;
import com.glassgo.platform.iam.domain.model.services.RoleQueryService;
import com.glassgo.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Application Service implementation for handling role-related queries in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This service orchestrates the execution of queries that retrieve role data, coordinating between
 * the domain layer and persistence infrastructure. It provides read-only access to role information
 * for use in application logic and user interfaces.
 * </p>
 *
 * @see RoleQueryService
 */
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
    private final RoleRepository roleRepository;

    /**
     * Constructs a new RoleQueryServiceImpl with the required dependencies.
     *
     * @param roleRepository the repository for role persistence operations
     */
    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Handles the query to retrieve all roles from the system.
     * <p>
     * This method processes the {@link GetAllRolesQuery} by delegating to the repository
     * to fetch all persisted roles. The result can be used for administrative interfaces
     * or role selection components.
     * </p>
     *
     * @param query the {@link GetAllRolesQuery} specifying the retrieval criteria
     * @return a list of all {@link Role} entities in the system
     */
    @Override
    public List<Role> handle(GetAllRolesQuery query) {
        return roleRepository.findAll();
    }

    /**
     * Handles the query to retrieve a specific role by its name.
     * <p>
     * This method processes the {@link GetRoleByNameQuery} by searching for a role
     * matching the provided enumerated name. It returns an Optional to handle cases
     * where the role does not exist.
     * </p>
     *
     * @param query the {@link GetRoleByNameQuery} containing the role name to search for
     * @return an {@link Optional} containing the {@link Role} entity if found, or empty otherwise
     */
    @Override
    public Optional<Role> handle(GetRoleByNameQuery query) {
        return roleRepository.findByName(query.name());
    }
}
