package com.glassgo.platform.analytics.domain.model.commands;

public record CreateReportCommand(String sourceId) {
    public CreateReportCommand {
        if (sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceId must not be null or blank");
        }
    }
}
