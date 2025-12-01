package com.glassgo.platform.iam.application.internal.outboundservices.hashing;

/**
 * Outbound Service interface for password hashing operations in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This interface defines the contract for secure password encoding and verification.
 * Implementations should use strong, adaptive hashing algorithms to protect user credentials.
 * It serves as an abstraction over the underlying hashing technology, allowing for
 * easy replacement or upgrading of hashing strategies.
 * </p>
 */
public interface HashingService {
    /**
     * Encodes a raw password into a hashed representation.
     * <p>
     * This method transforms a plain-text password into a secure hash that can be safely
     * stored in the database. The hashing process should include salting and be computationally
     * intensive to resist brute-force attacks.
     * </p>
     *
     * @param rawPassword the plain-text password to be encoded
     * @return the encoded (hashed) password as a string
     */
    String encode(CharSequence rawPassword);

    /**
     * Verifies if a raw password matches an encoded (hashed) password.
     * <p>
     * This method compares a plain-text password against its hashed counterpart to determine
     * if they match. It should handle the comparison securely to prevent timing attacks.
     * </p>
     *
     * @param rawPassword the plain-text password to verify
     * @param encodedPassword the previously encoded password to compare against
     * @return true if the raw password matches the encoded password, false otherwise
     */
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
