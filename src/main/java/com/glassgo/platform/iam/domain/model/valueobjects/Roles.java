package com.glassgo.platform.iam.domain.model.valueobjects;

/**
 * Enumeration of user roles within the Identity and Access Management (IAM) bounded context.
 * These value objects define the authorization levels for users, encapsulating role-based
 * access control logic. Roles determine permissions and capabilities within the system,
 * ensuring that domain invariants related to security and access are maintained.
 */
public enum Roles {
    ROLE_USER,
    ROLE_ADMIN
}
