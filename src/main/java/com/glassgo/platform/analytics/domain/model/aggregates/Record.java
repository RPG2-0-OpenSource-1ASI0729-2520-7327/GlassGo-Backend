package com.glassgo.platform.analytics.domain.model.aggregates;

import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand; // This will need to be updated later
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Aggregate root representing an Order Tracking Record in the analytics domain.
 * <p>
 * This entity stores key timestamps related to an order's lifecycle,
 * providing a detailed record of its journey.
 * </p>
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor // Required for JPA
public class Record extends AbstractAggregateRoot<Record> { // Renamed from Report to Record

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false)
    private LocalDateTime createdAt; // Order creation timestamp

    @Column
    private LocalDateTime packagingStartedAt;

    @Column
    private LocalDateTime shippedAt;

    @Column
    private LocalDateTime receivedAt;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date reportGeneratedAt; // Timestamp when this record entity was created

    @Column(nullable = false)
    @LastModifiedDate
    private Date reportUpdatedAt; // Timestamp when this record entity was last updated

    /**
     * Constructs a new Record aggregate from the given command.
     * Initializes the record with the order tracking timestamps provided in the command.
     *
     * @param command The command containing the data to create the record.
     */
    public Record(CreateReportCommand command) { // Constructor now takes CreateReportCommand
        this.orderId = command.orderId();
        this.createdAt = command.createdAt();
        this.packagingStartedAt = command.packagingStartedAt();
        this.shippedAt = command.shippedAt();
        this.receivedAt = command.receivedAt();
    }

    /**
     * Constructor for creating a Record with specific tracking details.
     *
     * @param orderId            The unique identifier of the order.
     * @param createdAt          The timestamp when the order was created.
     * @param packagingStartedAt The timestamp when packaging began.
     * @param shippedAt          The timestamp when the order was shipped.
     * @param receivedAt         The timestamp when the order was received.
     */
    public Record(String orderId, LocalDateTime createdAt, LocalDateTime packagingStartedAt, LocalDateTime shippedAt, LocalDateTime receivedAt) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.packagingStartedAt = packagingStartedAt;
        this.shippedAt = shippedAt;
        this.receivedAt = receivedAt;
    }
}
