package com.glassgo.platform.analytics.interfaces.rest.transform;

import com.glassgo.platform.analytics.domain.model.commands.CreateReportCommand; // This will need to be updated later
import com.glassgo.platform.analytics.interfaces.rest.resources.CreateReportResource; // This will need to be updated later

/**
 * Assembler for transforming CreateRecordResource into CreateRecordCommand.
 * <p>
 * This utility class bridges the interface layer and the domain layer by converting
 * external resource representations into domain commands, ensuring clean separation
 * of concerns and adherence to DDD principles.
 * </p>
 */
public class CreateReportCommandFromResourceAssembler { // This will need to be renamed later
    /**
     * Transforms a {@link CreateReportResource} into a {@link CreateReportCommand} for domain processing.
     * Validates the input resource and maps its attributes to the command structure.
     *
     * @param resource The resource to transform.
     * @return The corresponding command.
     * @throws IllegalArgumentException if the resource is null.
     */
    public static CreateReportCommand toCommandFromResource(CreateReportResource resource) { // This will need to be updated later
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
