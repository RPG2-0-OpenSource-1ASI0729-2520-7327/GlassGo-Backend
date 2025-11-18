package com.glassgo.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Entry point for the GlassGo platform Spring Boot application.
 */
@EnableJpaAuditing
@SpringBootApplication
public class GlassgoPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlassgoPlatformApplication.class, args);
    }

}
