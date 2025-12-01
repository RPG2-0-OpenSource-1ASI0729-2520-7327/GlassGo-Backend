package com.glassgo.platform.iam.interfaces.rest.transform;

import com.glassgo.platform.iam.domain.model.entities.Role;
import com.glassgo.platform.iam.interfaces.rest.resources.RoleResource;

/**
 * Assembler to convert a Role entity to a RoleResource.
 */
public class RoleResourceFromEntityAssembler {
    /**
     * Converts a Role entity to a RoleResource.
     * @param role the Role entity to convert
     * @return the RoleResource
     */
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
