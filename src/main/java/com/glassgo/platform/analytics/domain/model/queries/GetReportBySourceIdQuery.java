package com.glassgo.platform.analytics.domain.model.queries;

public record GetReportBySourceIdQuery(String sourceId) {
    public GetReportBySourceIdQuery {
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId must not be null or blank");
        }
    }
}
