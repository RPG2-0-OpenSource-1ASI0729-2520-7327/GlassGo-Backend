package com.glassgo.platform.analytics.domain.services;

import com.glassgo.platform.analytics.domain.model.aggregates.Report;
import com.glassgo.platform.analytics.domain.model.queries.GetReportByIdQuery;
import com.glassgo.platform.analytics.domain.model.queries.GetReportBySourceIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReportQueryService {
    List<Report>        handle(GetReportByIdQuery query);
    Optional<Report>    handle(GetReportBySourceIdQuery query);
}
