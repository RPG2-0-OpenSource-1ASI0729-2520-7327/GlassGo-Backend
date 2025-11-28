package com.glassgo.platform.analytics.interfaces.rest.transform;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.interfaces.rest.resources.ReportResource;

public class ReportFromEntityAssembler {
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
