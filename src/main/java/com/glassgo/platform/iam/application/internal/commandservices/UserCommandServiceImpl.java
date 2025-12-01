package com.glassgo.platform.iam.application.internal.commandservices;

import com.glassgo.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.glassgo.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.glassgo.platform.iam.domain.model.aggregates.User;
import com.glassgo.platform.iam.domain.model.commands.SignInCommand;
import com.glassgo.platform.iam.domain.model.commands.SignUpCommand;
import com.glassgo.platform.iam.domain.model.services.UserCommandService;
import com.glassgo.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.glassgo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Application Service implementation for handling user-related commands in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This service orchestrates user authentication and registration processes, coordinating between
 * domain services, outbound services (hashing and token generation), and persistence layers.
 * It implements business logic for sign-in and sign-up operations while ensuring security and data integrity.
 * </p>
 *
 * @see UserCommandService
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    private final RoleRepository roleRepository;

    /**
     * Constructs a new UserCommandServiceImpl with the required dependencies.
     *
     * @param userRepository the repository for user persistence operations
     * @param hashingService the service for password hashing and verification
     * @param tokenService the service for JWT token generation
     * @param roleRepository the repository for role persistence operations
     */
    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    /**
     * Handles the sign-in command to authenticate a user.
     * <p>
     * This method processes the {@link SignInCommand} by verifying the user's credentials against
     * the stored data. It checks for user existence, validates the password using the hashing service,
     * and generates a JWT token upon successful authentication.
     * </p>
     *
     * @param command the sign-in command containing the username and password
     * @return an optional containing an {@link ImmutablePair} of the authenticated user and JWT token, or empty if authentication fails
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handles the sign-up command to register a new user.
     * <p>
     * This method processes the {@link SignUpCommand} by validating uniqueness of the username,
     * resolving the provided roles from the repository, hashing the password, and persisting the new user.
     * It ensures that all referenced roles exist before creating the user account.
     * </p>
     *
     * @param command the sign-up command containing the username, password, and roles
     * @return an optional containing the newly created user, or empty if registration fails
     * @throws RuntimeException if the username already exists or a referenced role is not found
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role name not found"))).toList();
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }
}
