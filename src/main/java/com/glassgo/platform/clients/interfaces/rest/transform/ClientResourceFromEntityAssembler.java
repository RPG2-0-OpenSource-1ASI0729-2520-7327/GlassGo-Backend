package com.glassgo.platform.clients.interfaces.rest.transform;

import com.glassgo.platform.clients.domain.model.aggregates.Client;
import com.glassgo.platform.clients.interfaces.rest.resources.ClientResource;

public class ClientResourceFromEntityAssembler {
    public static ClientResource toResourceFromEntity(Client entity) {
        return new ClientResource(
                entity.getId(),
                entity.getUserId(),
                entity.getSubscriptionId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhone()
        );
    }
}
