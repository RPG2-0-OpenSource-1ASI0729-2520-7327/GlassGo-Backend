package com.glassgo.platform.analytics.interfaces.rest.transform;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.interfaces.rest.resources.ReportResource;

/**
 * Assembler for transforming Report domain entities into ReportResource representations.
 * This utility class facilitates the conversion from internal domain aggregates to external
 * resource formats suitable for REST API responses, maintaining encapsulation and separation
 * between domain and interface layers in accordance with DDD.
 */
public class ReportFromEntityAssembler {
    /**
     * Transforms a Report aggregate into a ReportResource for external consumption.
     * Maps the essential attributes of the domain entity to the resource structure,
     * hiding internal details and exposing only client-relevant data.
     *
     * @param entity the Report domain entity to transform
     * @return the corresponding ReportResource
     * @throws IllegalArgumentException if the entity is null
     */
    public static ReportResource toResourceFromEntity(Report entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        return new ReportResource(
                entity.getId(),
                entity.getSourceId()
        );
    }
}
