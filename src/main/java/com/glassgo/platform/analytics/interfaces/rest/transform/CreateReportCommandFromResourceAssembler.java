package com.glassgo.platform.analytics.interfaces.rest.transform;

import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand;
import com.glassgo.platform.analytics.interfaces.rest.resources.CreateReportResource;

public class CreateReportCommandFromResourceAssembler {
    public static CreateReportCommand toCommandFromResource(CreateReportResource resource) {
        if (resource == null) {
            throw new IllegalArgumentException("resource must not be null");
        }
        return new CreateReportCommand(
                resource.sourceId()
        );
    }
}
