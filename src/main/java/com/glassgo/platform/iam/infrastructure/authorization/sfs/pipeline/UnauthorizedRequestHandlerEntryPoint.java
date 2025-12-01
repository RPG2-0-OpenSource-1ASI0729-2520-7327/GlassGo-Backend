package com.glassgo.platform.iam.infrastructure.authorization.sfs.pipeline;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Spring Security AuthenticationEntryPoint for handling unauthorized requests in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This component handles authentication failures by sending appropriate HTTP responses
 * for unauthorized access attempts. It logs security events and returns a 401 Unauthorized
 * status to clients when authentication is required but not provided or invalid.
 * </p>
 *
 * @see AuthenticationEntryPoint
 */

@Component
public class UnauthorizedRequestHandlerEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnauthorizedRequestHandlerEntryPoint.class);

    /**
     * Handles unauthorized access attempts by logging the error and sending an HTTP 401 response.
     * <p>
     * This method is invoked by Spring Security when an authentication exception occurs,
     * such as missing or invalid credentials. It ensures that unauthorized requests are
     * properly logged and clients receive appropriate error responses.
     * </p>
     *
     * @param request the HTTP request that triggered the authentication exception
     * @param response the HTTP response to be sent to the client
     * @param authenticationException the exception detailing the authentication failure
     * @throws IOException if an I/O error occurs while sending the response
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        LOGGER.error("Unauthorized request: {}", authenticationException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request detected");
    }
}
