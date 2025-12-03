package com.glassgo.platform.iam.infrastructure.authorization.sfs.configuration;

import com.glassgo.platform.iam.infrastructure.authorization.sfs.pipeline.BearerAuthorizationRequestFilter;
import com.glassgo.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.glassgo.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * Spring Security configuration for the Identity and Access Management (IAM) bounded context.
 * <p>
 * This configuration class sets up comprehensive web security for the application, including
 * JWT-based authentication, CORS settings, and authorization rules. It configures stateless
 * session management, disables CSRF for API endpoints, and establishes the security filter chain
 * with Bearer token authentication. The configuration ensures secure access to protected resources
 * while allowing public access to authentication and documentation endpoints.
 * </p>
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private final UserDetailsService userDetailsService;

    private final BearerTokenService tokenService;

    private final BCryptHashingService hashingService;

    private final AuthenticationEntryPoint unauthorizedRequestHandler;

    /**
     * Creates the Bearer Authorization Request Filter bean.
     * <p>
     * This filter intercepts incoming requests to validate JWT tokens and set up
     * the security context for authenticated users.
     * </p>
     *
     * @return the configured BearerAuthorizationRequestFilter
     * @see BearerAuthorizationRequestFilter
     */
    @Bean
    public BearerAuthorizationRequestFilter authorizationRequestFilter() {
        return new BearerAuthorizationRequestFilter(tokenService, userDetailsService);
    }

    /**
     * Creates the AuthenticationManager bean.
     * <p>
     * This manager handles authentication requests and coordinates with authentication providers.
     * </p>
     *
     * @param authenticationConfiguration the Spring Security authentication configuration
     * @return the AuthenticationManager instance
     * @throws Exception if configuration fails
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Creates the DaoAuthenticationProvider bean.
     * <p>
     * This provider handles username/password authentication using the configured
     * UserDetailsService and password encoder.
     * </p>
     *
     * @return the configured DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(hashingService);
        return authenticationProvider;
    }

    /**
     * Creates the PasswordEncoder bean.
     * <p>
     * This encoder uses the BCrypt hashing service for secure password encoding and verification.
     * </p>
     *
     * @return the PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return hashingService;
    }

    /**
     * Creates the SecurityFilterChain bean with comprehensive security configuration.
     * <p>
     * This method configures HTTP security with CORS support, disables CSRF, sets up
     * stateless session management, defines authorization rules for different endpoints,
     * and integrates the authentication provider and Bearer token filter.
     * </p>
     *
     * @param http the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(configurer -> configurer.configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("*"));
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        }));
        http.csrf(csrfConfigurer -> csrfConfigurer.disable())
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedRequestHandler))
                .sessionManagement( customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/api/v1/authentication/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/webjars/**").permitAll()
                        .anyRequest().authenticated());
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authorizationRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    /**
     * Constructor for WebSecurityConfiguration.
     * <p>
     * Initializes the configuration with required dependencies for security setup.
     * </p>
     *
     * @param userDetailsService the service for loading user details
     * @param tokenService the service for JWT token operations
     * @param hashingService the service for password hashing
     * @param authenticationEntryPoint the handler for unauthorized requests
     */
    public WebSecurityConfiguration(@Qualifier("defaultUserDetailsService") UserDetailsService userDetailsService, BearerTokenService tokenService, BCryptHashingService hashingService, AuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.unauthorizedRequestHandler = authenticationEntryPoint;
    }
}
