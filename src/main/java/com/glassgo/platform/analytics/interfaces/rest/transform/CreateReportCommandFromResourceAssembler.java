package com.glassgo.platform.analytics.interfaces.rest.transform;

import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand;
import com.glassgo.platform.analytics.interfaces.rest.resources.CreateReportResource;

/**
 * Assembler for transforming CreateReportResource into CreateReportCommand.
 * <p>
 * This utility class bridges the interface layer and the domain layer by converting
 * external resource representations into domain commands, ensuring clean separation
 * of concerns and adherence to DDD principles.
 * </p>
 */
public class CreateReportCommandFromResourceAssembler {
    /**
     * Transforms a {@link CreateReportResource} into a {@link CreateReportCommand} for domain processing.
     * Validates the input resource and maps its attributes to the command structure.
     *
     * @param resource The resource to transform.
     * @return The corresponding command.
     * @throws IllegalArgumentException if the resource is null.
     */
    public static CreateReportCommand toCommandFromResource(CreateReportResource resource) {
        if (resource == null) {
            throw new IllegalArgumentException("resource must not be null");
        }
        return new CreateReportCommand(
                resource.orderId(),
                resource.createdAt(),
                resource.packagingStartedAt(),
                resource.shippedAt(),
                resource.receivedAt()
        );
    }
}
