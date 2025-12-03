package com.glassgo.platform.iam.infrastructure.hashing.bcrypt.services;

import com.glassgo.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Infrastructure implementation of the BCrypt hashing service for password security.
 * <p>
 * This service provides concrete implementation of password encoding and verification
 * using the BCrypt algorithm. BCrypt is an adaptive hashing function designed to resist
 * brute-force attacks by incorporating a salt and configurable computational cost.
 * </p>
 *
 * @see BCryptHashingService
 */
@Service
public class HashingServiceImpl implements BCryptHashingService {
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructs a new HashingServiceImpl with a default BCryptPasswordEncoder.
     * The encoder uses the default strength (10 rounds) for hashing.
     */
    HashingServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Encodes a raw password using the BCrypt algorithm.
     * <p>
     * This method generates a salted hash of the password, making it suitable for secure storage.
     * Each call produces a different hash even for the same password due to random salt generation.
     * </p>
     *
     * @param rawPassword the plain-text password to be hashed
     * @return the BCrypt-encoded password string
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Verifies if a raw password matches a previously encoded password.
     * <p>
     * This method compares the raw password against the encoded version by re-hashing
     * the raw password with the same salt and comparing the results.
     * </p>
     *
     * @param rawPassword the plain-text password to verify
     * @param encodedPassword the previously encoded password to compare against
     * @return true if the raw password matches the encoded password, false otherwise
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
