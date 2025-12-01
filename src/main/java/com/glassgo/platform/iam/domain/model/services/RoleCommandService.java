package com.glassgo.platform.iam.domain.model.services;

import com.glassgo.platform.iam.domain.model.commands.SeedRolesCommand;

/**
 * Domain Service for handling role-related commands in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This interface defines the contract for executing commands that modify role data, such as seeding
 * initial roles into the system. It encapsulates domain logic for role management operations,
 * ensuring that business rules are applied consistently.
 * </p>
 */
public interface RoleCommandService {
    /**
     * Handles the seeding of default roles into the system.
     * <p>
     * This method processes the {@link SeedRolesCommand} to initialize the role repository with
     * predefined roles (e.g., ROLE_USER, ROLE_ADMIN). It ensures that essential roles are available
     * for user assignment and maintains system invariants related to role availability.
     * </p>
     *
     * @param command the {@link SeedRolesCommand} containing the seeding instructions
     */
    void handle(SeedRolesCommand command);
}
