package com.glassgo.platform.iam.infrastructure.persistence.jpa.repositories;

import com.glassgo.platform.iam.domain.model.entities.Role;
import com.glassgo.platform.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Role entity persistence operations in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This interface provides data access methods for Role entities, extending JpaRepository
 * for standard CRUD operations. It includes custom query methods for role lookup and
 * existence checks, supporting the domain services' persistence needs.
 * </p>
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its enumerated name.
     * <p>
     * This method queries the database for a role matching the provided Roles enum value,
     * useful for role validation and assignment operations.
     * </p>
     *
     * @param name the enumerated name of the role to find
     * @return an Optional containing the Role if found, or empty otherwise
     */
    Optional<Role> findByName(Roles name);

    /**
     * Checks if a role exists by its enumerated name.
     * <p>
     * This method verifies the existence of a role in the database without retrieving
     * the full entity, optimizing for existence checks during role seeding or validation.
     * </p>
     *
     * @param name the enumerated name of the role to check
     * @return true if a role with the given name exists, false otherwise
     */
    boolean existsByName(Roles name);
}
