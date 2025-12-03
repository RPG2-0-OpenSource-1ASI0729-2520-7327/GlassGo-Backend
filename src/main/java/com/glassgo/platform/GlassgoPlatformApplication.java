package com.glassgo.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main application class and entry point for the GlassGo platform.
 * This Spring Boot application implements a Domain-Driven Design (DDD) architecture,
 * organizing the system into bounded contexts such as analytics and IAM. It enables
 * JPA auditing for automatic timestamp management on domain entities.
 */
@EnableJpaAuditing
@SpringBootApplication
public class GlassgoPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlassgoPlatformApplication.class, args);
    }

}
