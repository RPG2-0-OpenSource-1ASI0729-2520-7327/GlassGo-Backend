package com.glassgo.platform.iam.infrastructure.authorization.sfs.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

/**
 * Builder utility for creating Spring Security UsernamePasswordAuthenticationToken objects in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This utility class constructs authentication tokens for JWT-based authentication,
 * setting the user details as the principal and including request-specific details
 * for security context establishment.
 * </p>
 */
public class UsernamePasswordAuthenticationTokenBuilder {

    /**
     * Builds a UsernamePasswordAuthenticationToken for authenticated users.
     * <p>
     * This method creates an authentication token with the user details as the principal,
     * null credentials (since JWT handles authentication), and the user's authorities.
     * It also sets web authentication details from the HTTP request for additional context.
     * </p>
     *
     * @param principal the UserDetails object representing the authenticated user
     * @param request the HTTP servlet request for extracting authentication details
     * @return a configured UsernamePasswordAuthenticationToken for the security context
     * @see UsernamePasswordAuthenticationToken
     * @see UserDetails
     */
    public static UsernamePasswordAuthenticationToken build(UserDetails principal, HttpServletRequest request) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }
}
