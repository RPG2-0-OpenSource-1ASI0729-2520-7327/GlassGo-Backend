package com.glassgo.platform.iam.infrastructure.tokens.jwt.services;

import com.glassgo.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

/**
 * Infrastructure implementation of JWT token service for secure authentication.
 * <p>
 * This service provides concrete implementation of token generation, validation, and parsing
 * using JSON Web Tokens (JWT). It handles Bearer token extraction from HTTP requests and
 * ensures secure token-based authentication with configurable expiration and cryptographic signing.
 * </p>
 *
 * @see BearerTokenService
 */
@Service
public class TokenServiceImpl implements BearerTokenService {
    private final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String AUTHORIZATION_PARAMETER_NAME = "Authorization";
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    private static final int TOKEN_BEGIN_INDEX = 7;


    @Value("${authorization.jwt.secret}")
    private String secret;

    @Value("${authorization.jwt.expiration.days}")
    private int expirationDays;

    /**
     * Generates a JWT token from an Authentication object.
     * <p>
     * This method extracts the username from the authentication principal and creates
     * a signed JWT token with default expiration settings.
     * </p>
     *
     * @param authentication the Spring Security Authentication object containing user details
     * @return a JWT token string for the authenticated user
     * @see Authentication
     */
    @Override
    public String generateToken(Authentication authentication) {
        return buildTokenWithDefaultParameters(authentication.getName());
    }

    /**
     * Generates a JWT token for the specified username.
     * <p>
     * This method creates a signed JWT token containing the username as the subject,
     * with standard claims like issued-at and expiration dates.
     * </p>
     *
     * @param username the username to include in the token's subject claim
     * @return a JWT token string representing the user
     */
    public String generateToken(String username) {
        return buildTokenWithDefaultParameters(username);
    }

    /**
     * This method generates a JWT token from a username and a secret.
     * It uses the default expiration days from the application.properties file.
     * @param username the username
     * @return String the JWT token
     */
    private String buildTokenWithDefaultParameters(String username) {
        var issuedAt = new Date();
        var expiration = DateUtils.addDays(issuedAt, expirationDays);
        var key = getSigningKey();
        return Jwts.builder()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    /**
     * Extracts the username from a JWT token.
     * <p>
     * This method parses the token and retrieves the subject claim, which contains the username.
     * It verifies the token's signature before extracting claims.
     * </p>
     *
     * @param token the JWT token to extract the username from
     * @return the username contained in the token's subject claim
     * @throws RuntimeException if the token is invalid or cannot be parsed
     */
    @Override
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Validates the integrity and expiration of a JWT token.
     * <p>
     * This method verifies the token's signature, checks for expiration, and ensures
     * the token structure is valid. It logs specific error messages for different
     * types of validation failures.
     * </p>
     *
     * @param token the JWT token to validate
     * @return true if the token is valid and not expired, false otherwise
     */
    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            LOGGER.info("Token is valid");
            return true;
        }  catch (SignatureException e) {
            LOGGER.error("Invalid JSON Web Token Signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JSON Web Token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JSON Web Token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JSON Web Token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JSON Web Token claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Extract a claim from a token
     * @param token the token
     * @param claimsResolvers the claims resolver
     * @param <T> the type of the claim
     * @return T the claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Extract all claims from a token
     * @param token the token
     * @return Claims the claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    /**
     * Get the signing key
     * @return SecretKey the signing key
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isTokenPresentIn(String authorizationParameter) {
        return StringUtils.hasText(authorizationParameter);
    }

    private boolean isBearerTokenIn(String authorizationParameter) {
        return authorizationParameter.startsWith(BEARER_TOKEN_PREFIX);
    }

    private String extractTokenFrom(String authorizationHeaderParameter) {
        return authorizationHeaderParameter.substring(TOKEN_BEGIN_INDEX);
    }

    private String getAuthorizationParameterFrom(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_PARAMETER_NAME);
    }

    /**
     * Extracts the Bearer token from an HTTP request.
     * <p>
     * This method retrieves the Authorization header from the request, checks if it contains
     * a Bearer token, and extracts the token value. Returns null if no valid Bearer token
     * is present in the request.
     * </p>
     *
     * @param request the HttpServletRequest containing the authorization header
     * @return the JWT token string if present and valid, null otherwise
     */
    @Override
    public String getBearerTokenFrom(HttpServletRequest request) {
        String parameter = getAuthorizationParameterFrom(request);
        if (isTokenPresentIn(parameter) && isBearerTokenIn(parameter)) return extractTokenFrom(parameter);
        return null;
    }

}
