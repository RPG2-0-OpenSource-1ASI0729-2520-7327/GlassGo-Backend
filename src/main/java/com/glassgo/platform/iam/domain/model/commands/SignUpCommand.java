package com.glassgo.platform.iam.domain.model.commands;

import com.glassgo.platform.iam.domain.model.entities.Role;

import java.util.List;

/**
 * Command to register a new user in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This record carries the necessary data to create a new user account, including credentials
 * and initial roles. It is used by the application service to instantiate and persist a new
 * User aggregate, ensuring that all required fields are provided and valid.
 * </p>
 *
 * @param username the desired username for the new user
 * @param password the password for the new user
 * @param roles the list of roles to assign to the new user
 *
 * @see com.glassgo.platform.iam.domain.model.aggregates.User
 * @see com.glassgo.platform.iam.domain.model.entities.Role
 */
public record SignUpCommand(String username, String password, List<Role> roles) {
    /**
     * Compact constructor for SignUpCommand with validation.
     * Ensures that username and password are not null or blank.
     */
    public SignUpCommand {
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("username cannot be null or blank");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("password cannot be null or blank");
        // roles can be null or empty, as validation happens in User.addRoles
    }
}
