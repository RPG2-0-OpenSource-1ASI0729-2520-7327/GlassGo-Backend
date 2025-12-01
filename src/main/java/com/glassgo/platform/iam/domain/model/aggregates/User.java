package com.glassgo.platform.iam.domain.model.aggregates;

import com.glassgo.platform.iam.domain.model.entities.Role;
import com.glassgo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User Aggregate Root in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This aggregate encapsulates the core business logic for user management, including
 * authentication, authorization, and role assignments. As an aggregate root, it ensures
 * consistency and invariants within the user domain, such as unique usernames and valid
 * role associations. The User aggregate is responsible for maintaining the integrity of
 * user-related data and enforcing domain rules.
 * </p>
 * <p>
 * Key responsibilities:
 * - Managing user credentials (username and password).
 * - Associating users with roles for access control.
 * - Ensuring data validation and business rule enforcement.
 * </p>
 *
 * @see AuditableAbstractAggregateRoot
 * @see Role
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    /**
     * The unique username of the user, used for identification and authentication.
     * Must be non-blank and not exceed 50 characters.
     */
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    /**
     * The hashed password of the user, used for authentication.
     * Must be non-blank and not exceed 120 characters.
     */
    @NotBlank
    @Size(max = 120)
    private String password;

    /**
     * The set of roles assigned to the user, defining their permissions and access levels.
     * Fetched eagerly to ensure roles are always available for authorization checks.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(	name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    /**
     * Default constructor for JPA.
     * Initializes the roles set to an empty HashSet.
     */
    public User() {
        this.roles = new HashSet<>();
    }

    /**
     * Constructs a new User with the specified username and password.
     * Initializes the roles set to an empty HashSet.
     *
     * @param username the unique username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
    }

    /**
     * Constructs a new User with the specified username, password, and roles.
     * Adds the provided roles to the user after validation.
     *
     * @param username the unique username of the user
     * @param password the password of the user
     * @param roles the list of roles to assign to the user
     */
    public User(String username, String password, List<Role> roles) {
        this(username, password);
        addRoles(roles);
    }

    /**
     * Adds a single role to the user's set of roles.
     * <p>
     * This method enforces domain invariants by ensuring that only valid roles are added.
     * The role is added directly to the set without additional validation beyond what is
     * inherent in the Role entity.
     * </p>
     *
     * @param role the role to add; must not be null
     * @return the user instance with the added role, allowing for method chaining
     */
    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    /**
     * Adds a list of roles to the user's set of roles after validation.
     * <p>
     * This method delegates to {@link Role#validateRoleSet(List)} to ensure that the
     * provided roles meet domain constraints. If the list is null or empty, a default
     * role (ROLE_USER) is assigned to maintain system invariants.
     * </p>
     *
     * @param roles the list of roles to add; if null or empty, defaults to ROLE_USER
     * @return the user instance with the added roles, allowing for method chaining
     * @see Role#validateRoleSet(List)
     */
    public User addRoles(List<Role> roles) {
        var validatedRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validatedRoleSet);
        return this;
    }

}
