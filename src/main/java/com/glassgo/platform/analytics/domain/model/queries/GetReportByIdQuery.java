package com.glassgo.platform.analytics.domain.model.queries;

public record GetReportByIdQuery(Long id) {
    public GetReportByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id must not be null or less than or equal to zero");
        }
    }
}
