package com.glassgo.platform.iam.domain.model.services;

import com.glassgo.platform.iam.domain.model.aggregates.User;
import com.glassgo.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.glassgo.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.glassgo.platform.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Domain Service for handling user-related queries in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This interface defines the contract for executing queries that retrieve user data without modifying
 * the domain state. It provides read-only access to user information, supporting operations like
 * user listing, profile retrieval, and authentication support.
 * </p>
 */
public interface UserQueryService {
    /**
     * Handles the query to retrieve all users in the system.
     * <p>
     * This method processes the {@link GetAllUsersQuery} to fetch a complete list of user aggregates,
     * typically used for administrative dashboards or reporting. Access should be restricted to
     * authorized personnel due to privacy concerns.
     * </p>
     *
     * @param query the {@link GetAllUsersQuery} specifying the retrieval criteria
     * @return a list of {@link User} entities representing all users in the system
     */
    List<User> handle(GetAllUsersQuery query);

    /**
     * Handles the query to retrieve a specific user by their ID.
     * <p>
     * This method processes the {@link GetUserByIdQuery} to find a user matching the given unique
     * identifier. It returns an Optional to handle cases where the user does not exist.
     * </p>
     *
     * @param query the {@link GetUserByIdQuery} containing the user ID to search for
     * @return an {@link Optional} containing the {@link User} entity if found, or empty otherwise
     */
    Optional<User> handle(GetUserByIdQuery query);

    /**
     * Handles the query to retrieve a specific user by their username.
     * <p>
     * This method processes the {@link GetUserByUsernameQuery} to find a user matching the given
     * username. It is commonly used during authentication or for user profile access.
     * </p>
     *
     * @param query the {@link GetUserByUsernameQuery} containing the username to search for
     * @return an {@link Optional} containing the {@link User} entity if found, or empty otherwise
     */
    Optional<User> handle(GetUserByUsernameQuery query);
}
