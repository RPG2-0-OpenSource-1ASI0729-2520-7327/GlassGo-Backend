package com.glassgo.platform.iam.application.internal.outboundservices.tokens;

/**
 * Outbound Service interface for JWT token operations in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This interface defines the contract for generating, validating, and extracting information
 * from JSON Web Tokens (JWT). It provides secure token-based authentication mechanisms,
 * enabling stateless session management and authorization. Implementations should ensure
 * tokens are cryptographically secure and include appropriate expiration and claims.
 * </p>
 */
public interface TokenService {

    /**
     * Generates a JWT token for the specified username.
     * <p>
     * This method creates a signed token containing the username as a claim, along with
     * standard JWT fields like issuer, expiration, and issued-at time. The token can be
     * used for subsequent authenticated requests.
     * </p>
     *
     * @param username the username to include in the token's claims
     * @return a JWT token string representing the authenticated user
     */
    String generateToken(String username);

    /**
     * Extracts the username from a JWT token.
     * <p>
     * This method parses the token and retrieves the username claim without validating
     * the token's signature or expiration. It should be used in conjunction with
     * {@link #validateToken(String)} for complete token verification.
     * </p>
     *
     * @param token the JWT token to extract the username from
     * @return the username contained in the token's claims
     * @throws RuntimeException if the token cannot be parsed or the username claim is missing
     */
    String getUsernameFromToken(String token);

    /**
     * Validates the integrity and expiration of a JWT token.
     * <p>
     * This method verifies the token's signature, checks for expiration, and ensures
     * the token has not been tampered with. It does not extract claims but confirms
     * the token's authenticity.
     * </p>
     *
     * @param token the JWT token to validate
     * @return true if the token is valid and not expired, false otherwise
     */
    boolean validateToken(String token);
}
