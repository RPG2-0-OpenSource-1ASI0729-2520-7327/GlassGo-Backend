package com.glassgo.platform.shared.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Base mapped superclass that provides auditing fields for entities.
 * <p>
 * Fields provided: id, createdAt and updatedAt. The class is configured to
 * work with Spring Data JPA auditing (enable with @EnableJpaAuditing).
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableModel {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Getter
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
