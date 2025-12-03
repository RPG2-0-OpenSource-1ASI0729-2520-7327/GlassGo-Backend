package com.glassgo.platform.iam.infrastructure.authorization.sfs.services;

import com.glassgo.platform.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.glassgo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security UserDetailsService implementation for the Identity and Access Management (IAM) bounded context.
 * <p>
 * This service bridges the domain layer with Spring Security by loading user details
 * from the persistence layer. It retrieves User aggregates by username and converts
 * them into UserDetails objects for authentication and authorization purposes.
 * </p>
 */
@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a UserDetailsServiceImpl with the required repository.
     *
     * @param userRepository the repository for user persistence operations
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user details by username for Spring Security authentication.
     * <p>
     * This method queries the user repository for a user with the given username,
     * converts the domain User aggregate into a UserDetails object, and returns it
     * for authentication processing. If the user is not found, a UsernameNotFoundException
     * is thrown.
     * </p>
     *
     * @param username the username of the user to load
     * @return the UserDetails object containing the user's authentication information
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImpl.build(user);
    }
}
