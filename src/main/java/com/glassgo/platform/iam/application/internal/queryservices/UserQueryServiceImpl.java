package com.glassgo.platform.iam.application.internal.queryservices;

import com.glassgo.platform.iam.domain.model.aggregates.User;
import com.glassgo.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.glassgo.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.glassgo.platform.iam.domain.model.queries.GetUserByUsernameQuery;
import com.glassgo.platform.iam.domain.model.services.UserQueryService;
import com.glassgo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Application Service implementation for handling user-related queries in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This service orchestrates the execution of queries that retrieve user data, coordinating between
 * the domain layer and persistence infrastructure. It provides read-only access to user information
 * for use in application logic, authentication, and user management interfaces.
 * </p>
 *
 * @see UserQueryService
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    /**
     * Constructs a new UserQueryServiceImpl with the required dependencies.
     *
     * @param userRepository the repository for user persistence operations
     */
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Handles the query to retrieve all users from the system.
     * <p>
     * This method processes the {@link GetAllUsersQuery} by delegating to the repository
     * to fetch all persisted users. The result is typically used for administrative dashboards
     * or reporting, and should be accessed with appropriate authorization.
     * </p>
     *
     * @param query the {@link GetAllUsersQuery} specifying the retrieval criteria
     * @return a list of all {@link User} entities in the system
     * @see GetAllUsersQuery
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    /**
     * Handles the query to retrieve a specific user by their ID.
     * <p>
     * This method processes the {@link GetUserByIdQuery} by searching for a user
     * matching the provided unique identifier. It returns an Optional to handle cases
     * where the user does not exist.
     * </p>
     *
     * @param query the {@link GetUserByIdQuery} containing the user ID to search for
     * @return an {@link Optional} containing the {@link User} entity if found, or empty otherwise
     * @see GetUserByIdQuery
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    /**
     * Handles the query to retrieve a specific user by their username.
     * <p>
     * This method processes the {@link GetUserByUsernameQuery} by searching for a user
     * matching the provided username. It is commonly used during authentication processes
     * or for user profile retrieval.
     * </p>
     *
     * @param query the {@link GetUserByUsernameQuery} containing the username to search for
     * @return an {@link Optional} containing the {@link User} entity if found, or empty otherwise
     * @see GetUserByUsernameQuery
     */
    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }
}
