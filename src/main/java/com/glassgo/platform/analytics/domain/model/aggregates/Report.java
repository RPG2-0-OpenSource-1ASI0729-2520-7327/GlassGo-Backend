package com.glassgo.platform.analytics.domain.model.aggregates;

import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

public class Report extends AbstractAggregateRoot<Report> {

    private Long id;

    private String sourceId;

    private Date createdAt;

    private Date updatedAt;

    protected Report() {}

    public Report(CreateReportCommand command) {
        this.sourceId = command.sourceId();
    }
}
