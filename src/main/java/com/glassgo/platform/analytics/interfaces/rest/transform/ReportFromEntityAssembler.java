package com.glassgo.platform.analytics.interfaces.rest.transform;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.interfaces.rest.resources.ReportResource;

/**
 * Assembler for transforming Report domain entities into ReportResource representations.
 * <p>
 * This utility class facilitates the conversion from internal domain aggregates to external
 * resource formats suitable for REST API responses, maintaining encapsulation and separation
 * between domain and interface layers in accordance with DDD.
 * </p>
 */
public class ReportFromEntityAssembler {
    /**
     * Transforms a {@link Report} aggregate into a {@link ReportResource} for external consumption.
     * Maps the essential attributes of the domain entity to the resource structure,
     * hiding internal details and exposing only client-relevant data.
     *
     * @param entity The Report domain entity to transform.
     * @return The corresponding ReportResource.
     * @throws IllegalArgumentException if the entity is null.
     */
    public static ReportResource toResourceFromEntity(Report entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        return new ReportResource(
                entity.getId(),
                entity.getOrderId(),
                entity.getCreatedAt(),
                entity.getPackagingStartedAt(),
                entity.getShippedAt(),
                entity.getReceivedAt()
        );
    }
}
