package com.glassgo.platform.clients.interfaces.rest.resources;

public record CreateClientResource(
        Long userId,
        Long subscriptionId,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
