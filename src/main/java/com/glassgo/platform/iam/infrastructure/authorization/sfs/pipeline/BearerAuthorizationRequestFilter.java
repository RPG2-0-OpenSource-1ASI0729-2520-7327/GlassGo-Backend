package com.glassgo.platform.iam.infrastructure.authorization.sfs.pipeline;

import com.glassgo.platform.iam.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import com.glassgo.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Spring Security filter for Bearer token authorization in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This filter intercepts incoming HTTP requests to validate JWT Bearer tokens and establish
 * the security context for authenticated users. It extracts tokens from request headers,
 * validates them, and sets up the Spring Security authentication context if valid.
 * The filter ensures that subsequent request processing has access to the authenticated user's details.
 * </p>
 *
 * @see OncePerRequestFilter
 */
public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);
    private final BearerTokenService tokenService;


    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    /**
     * Constructs a BearerAuthorizationRequestFilter with required services.
     *
     * @param tokenService the service for JWT token operations
     * @param userDetailsService the service for loading user details
     */
    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Processes the HTTP request to authenticate users with Bearer tokens.
     * <p>
     * This method extracts the JWT token from the request, validates it, retrieves the username,
     * loads user details, and sets the authentication in the security context. If the token is
     * invalid or missing, the request continues without authentication.
     * </p>
     *
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @param filterChain the filter chain for continuing request processing
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = tokenService.getBearerTokenFrom(request);
            LOGGER.info("Token: {}", token);
            if (token != null && tokenService.validateToken(token)) {
                String username = tokenService.getUsernameFromToken(token);
                var userDetails = userDetailsService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));
            } else {
                LOGGER.info("Token is not valid");
            }

        } catch (Exception e) {
            LOGGER.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
