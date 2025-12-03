package com.glassgo.platform.iam.interfaces.rest.transform;

import com.glassgo.platform.iam.domain.model.entities.Role;
import com.glassgo.platform.iam.interfaces.rest.resources.RoleResource;

/**
 * Assembler for transforming Role domain entities into RoleResource representations.
 * <p>
 * This utility class provides static methods to convert domain objects into resource
 * objects suitable for REST API responses. It ensures that only appropriate data is
 * exposed to clients, maintaining the separation between domain and interface layers
 * as per DDD principles.
 * </p>
 */
public class RoleResourceFromEntityAssembler {
    /**
     * Converts a Role domain entity to a RoleResource for API responses.
     * <p>
     * This method maps the essential attributes of the Role entity to the resource
     * representation, transforming domain data into a format suitable for external consumption.
     * </p>
     *
     * @param role the Role entity to convert
     * @return the corresponding RoleResource with mapped data
     */
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
