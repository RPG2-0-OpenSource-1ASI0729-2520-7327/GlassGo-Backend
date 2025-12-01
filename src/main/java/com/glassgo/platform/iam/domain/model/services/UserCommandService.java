package com.glassgo.platform.iam.domain.model.services;

import com.glassgo.platform.iam.domain.model.aggregates.User;
import com.glassgo.platform.iam.domain.model.commands.SignInCommand;
import com.glassgo.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

/**
 * Domain Service for handling user-related commands in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This interface defines the contract for executing commands that create or authenticate users,
 * encapsulating the business logic for user registration and login processes. It ensures that
 * domain invariants are maintained during user lifecycle operations.
 * </p>
 */
public interface UserCommandService {
    /**
     * Handles the sign-in command to authenticate a user.
     * <p>
     * This method processes the {@link SignInCommand} to verify user credentials and generate
     * an authentication token if successful. It returns a pair containing the authenticated user
     * and the JWT token for session management.
     * </p>
     *
     * @param command the {@link SignInCommand} containing user credentials
     * @return an {@link Optional} containing an {@link ImmutablePair} of the authenticated {@link User} and JWT token string, or empty if authentication fails
     */
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    /**
     * Handles the sign-up command to register a new user.
     * <p>
     * This method processes the {@link SignUpCommand} to create a new user account with the provided
     * details. It validates the input, assigns default roles if necessary, and persists the new user.
     * </p>
     *
     * @param command the {@link SignUpCommand} containing user registration details
     * @return an {@link Optional} containing the newly created {@link User} entity, or empty if registration fails
     */
    Optional<User> handle(SignUpCommand command);
}
