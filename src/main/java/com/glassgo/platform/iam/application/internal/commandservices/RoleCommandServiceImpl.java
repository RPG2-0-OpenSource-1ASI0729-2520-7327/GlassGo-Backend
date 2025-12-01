package com.glassgo.platform.iam.application.internal.commandservices;

import com.glassgo.platform.iam.domain.model.commands.SeedRolesCommand;
import com.glassgo.platform.iam.domain.model.entities.Role;
import com.glassgo.platform.iam.domain.model.valueobjects.Roles;
import com.glassgo.platform.iam.domain.model.services.RoleCommandService;
import com.glassgo.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Application Service implementation for handling role-related commands in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This service orchestrates the execution of commands that modify role data, coordinating between
 * the domain layer and infrastructure. It ensures transactional integrity and applies application-level
 * logic for role management operations.
 * </p>
 *
 * @see RoleCommandService
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    /**
     * Constructs a new RoleCommandServiceImpl with the required dependencies.
     *
     * @param roleRepository the repository for role persistence operations
     */
    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Handles the seeding of default roles into the system.
     * <p>
     * This method processes the {@link SeedRolesCommand} by iterating through all defined {@link Roles}
     * and persisting them if they do not already exist. It ensures that the system has all necessary
     * roles available for user assignment without duplicates.
     * </p>
     *
     * @param command the {@link SeedRolesCommand} containing seeding instructions
     * @see SeedRolesCommand
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        } );
    }
}
