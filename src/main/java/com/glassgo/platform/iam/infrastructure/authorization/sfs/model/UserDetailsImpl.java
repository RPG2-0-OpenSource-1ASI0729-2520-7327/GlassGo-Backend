package com.glassgo.platform.iam.infrastructure.authorization.sfs.model;

import com.glassgo.platform.iam.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Spring Security UserDetails implementation for the Identity and Access Management (IAM) bounded context.
 * <p>
 * This class adapts the domain User aggregate to Spring Security's UserDetails interface,
 * providing user authentication and authorization information. It extracts roles from the
 * User aggregate and converts them into GrantedAuthority objects for security framework integration.
 * </p>
 */
@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructs a UserDetailsImpl with the specified authentication details.
     * <p>
     * This constructor sets up a user with full account status (non-expired, non-locked,
     * credentials non-expired, and enabled) suitable for active users.
     * </p>
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param authorities the collection of granted authorities (roles)
     */
    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    /**
     * Factory method to build UserDetailsImpl from a User domain aggregate.
     * <p>
     * This method extracts the username, password, and roles from the User aggregate,
     * converting roles into SimpleGrantedAuthority objects for Spring Security.
     * </p>
     *
     * @param user the User aggregate to convert
     * @return a new UserDetailsImpl instance with the user's authentication details
     */
    public static UserDetailsImpl build(User user) {
        var authorities = user.getRoles().stream()
                .map(role -> role.getName().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

}
