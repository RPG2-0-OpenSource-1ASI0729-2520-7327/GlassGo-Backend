package com.glassgo.platform.clients.interfaces.rest.transform;

import com.glassgo.platform.clients.domain.model.commands.CreateClientCommand;
import com.glassgo.platform.clients.interfaces.rest.resources.CreateClientResource;

public class CreateClientCommandFromResourceAssembler {
    public static CreateClientCommand toCommandFromResource(CreateClientResource resource) {
        return new CreateClientCommand(
                resource.userId(),
                resource.subscriptionId(),
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phone()
        );
    }
}
