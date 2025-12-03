package com.glassgo.platform.iam.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User aggregate persistence operations in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This interface provides data access methods for User aggregates, extending JpaRepository
 * for standard CRUD operations. It includes custom query methods for user lookup by username
 * and existence checks, supporting authentication and user management functionalities.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    /**
     * Finds a user by their username.
     * <p>
     * This method queries the database for a user matching the provided username,
     * essential for authentication processes and user profile retrieval.
     * </p>
     *
     * @param username the username of the user to find
     * @return an Optional containing the User aggregate if found, or empty otherwise
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user exists by their username.
     * <p>
     * This method verifies the existence of a user in the database without retrieving
     * the full aggregate, optimizing for uniqueness checks during user registration.
     * </p>
     *
     * @param username the username to check for existence
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByUsername(String username);
}
