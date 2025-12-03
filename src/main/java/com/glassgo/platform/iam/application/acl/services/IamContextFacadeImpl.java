package com.glassgo.platform.iam.application.acl.services;

import com.glassgo.platform.iam.domain.model.commands.SignUpCommand;
import com.glassgo.platform.iam.domain.model.entities.Role;
import com.glassgo.platform.iam.domain.model.queries.GetUserByIdQuery;
import com.glassgo.platform.iam.domain.model.queries.GetUserByUsernameQuery;
import com.glassgo.platform.iam.domain.model.services.UserCommandService;
import com.glassgo.platform.iam.domain.model.services.UserQueryService;
import com.glassgo.platform.iam.interfaces.acl.IamContextFacade;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Anti-Corruption Layer (ACL) Facade implementation for the Identity and Access Management (IAM) bounded context.
 * <p>
 * This facade provides a simplified interface for other bounded contexts to interact with the IAM domain
 * without coupling to its internal structure. It translates external requests into domain commands and queries,
 * shielding the IAM domain from external changes and ensuring loose coupling between bounded contexts.
 * </p>
 *
 * @see IamContextFacade
 */
@Service
public class IamContextFacadeImpl implements IamContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    /**
     * Constructs a new IamContextFacadeImpl with the required domain services.
     *
     * @param userCommandService the service for handling user commands
     * @param userQueryService the service for handling user queries
     */
    public IamContextFacadeImpl(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Creates a new user with the specified username and password, assigning the default role.
     * <p>
     * This method simplifies user creation for external contexts by automatically assigning
     * the default user role. It returns the ID of the created user or 0 if creation fails.
     * </p>
     *
     * @param username the desired username for the new user
     * @param password the password for the new user
     * @return the ID of the created user, or 0 if creation failed
     */
    @Override
    public Long createUser(String username, String password) {
        var signUpCommand = new SignUpCommand(username, password, List.of(Role.getDefaultRole()));
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Creates a new user with the specified username, password, and roles.
     * <p>
     * This method allows external contexts to create users with specific roles by providing
     * role names. Invalid role names are converted to Role objects, and the user is created
     * with the specified roles. Returns the ID of the created user or 0 if creation fails.
     * </p>
     *
     * @param username the desired username for the new user
     * @param password the password for the new user
     * @param roleNames the list of role names to assign; invalid names are ignored
     * @return the ID of the created user, or 0 if creation failed
     */
    @Override
    public Long createUser(String username, String password, List<String> roleNames) {
        var roles = roleNames != null ? roleNames.stream().map(Role::toRoleFromName).toList() : new ArrayList<Role>();
        var signUpCommand = new SignUpCommand(username, password, roles);
        var result = userCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Retrieves the user ID associated with the given username.
     * <p>
     * This method provides a way for external contexts to obtain a user's ID by username,
     * facilitating cross-context references. Returns 0 if the user is not found.
     * </p>
     *
     * @param username the username to search for
     * @return the ID of the user with the specified username, or 0 if not found
     */
    @Override
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        var result = userQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Retrieves the username associated with the given user ID.
     * <p>
     * This method allows external contexts to obtain a username by ID, useful for
     * displaying user information. Returns an empty string if the user is not found.
     * </p>
     *
     * @param userId the ID of the user to search for
     * @return the username of the user with the specified ID, or empty string if not found
     */
    @Override
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }

}
