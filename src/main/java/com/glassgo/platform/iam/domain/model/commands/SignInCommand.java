package com.glassgo.platform.iam.domain.model.commands;

/**
 * Command to authenticate a user in the Identity and Access Management (IAM) bounded context.
 * <p>
 * This record encapsulates the data required to perform a sign-in operation, carrying
 * the user's credentials to the application service layer. It ensures that the provided
 * username and password meet basic validation criteria before proceeding with authentication.
 * </p>
 *
 * @param username the username of the user attempting to sign in
 * @param password the password of the user attempting to sign in
 *
 * @see com.glassgo.platform.iam.domain.model.aggregates.User
 */
public record SignInCommand(String username, String password) {
    /**
     * Compact constructor for SignInCommand with validation.
     * Ensures that username and password are not null or blank.
     */
    public SignInCommand {
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("username cannot be null or blank");
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("password cannot be null or blank");
    }
}
