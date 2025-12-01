package com.glassgo.platform.analytics.domain.model.aggregates;

import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Aggregate root representing a Report in the analytics domain.
 * This entity encapsulates the core business logic and invariants for reports,
 * ensuring that all operations on reports maintain data consistency and integrity.
 * Reports are identified by a unique source ID and track creation and modification timestamps.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Report extends AbstractAggregateRoot<Report> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String sourceId;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    /**
     * Protected constructor for JPA and framework use.
     * Should not be used directly; use the public constructor with a command instead.
     */
    protected Report() {}

    /**
     * Creates a new Report aggregate from the given command.
     * Initializes the report with the source ID provided in the command.
     * Timestamps are automatically managed by auditing.
     *
     * @param command the command containing the data to create the report
     * @throws IllegalArgumentException if the command is null or invalid
     */
    public Report(CreateReportCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Command must not be null");
        }
        this.sourceId = command.sourceId();
    }
}
