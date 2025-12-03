package com.glassgo.platform.iam.domain.model.commands;

/**
 * Command to seed the system with default roles in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This record represents a command to initialize the role repository with predefined roles,
 * such as ROLE_USER and ROLE_ADMIN. It is typically used during system startup or migration
 * to ensure that essential roles are available for user assignment.
 * </p>
 */
public record SeedRolesCommand() {
}
