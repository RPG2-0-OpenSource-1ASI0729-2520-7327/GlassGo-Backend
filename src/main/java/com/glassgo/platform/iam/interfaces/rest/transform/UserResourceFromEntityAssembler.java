package com.glassgo.platform.iam.interfaces.rest.transform;

import com.glassgo.platform.iam.domain.model.aggregates.User;
import com.glassgo.platform.iam.domain.model.entities.Role;
import com.glassgo.platform.iam.interfaces.rest.resources.UserResource;

/**
 * Assembler for transforming User domain aggregates into UserResource representations.
 * <p>
 * This utility class provides static methods to convert User domain objects into resource
 * objects suitable for REST API responses. It handles the transformation of complex domain
 * relationships (such as roles) into simple data structures for external consumption,
 * ensuring data privacy and appropriate exposure.
 * </p>
 */
public class UserResourceFromEntityAssembler {
    /**
     * Converts a User domain aggregate to a UserResource for API responses.
     * <p>
     * This method maps the User entity's attributes and associated roles to the resource
     * representation. Role entities are transformed into their string names for simplicity
     * in the API response.
     * </p>
     *
     * @param user the User aggregate to convert
     * @return the corresponding UserResource with mapped data
     */
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
