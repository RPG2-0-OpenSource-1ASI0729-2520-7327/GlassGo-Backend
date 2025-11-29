package com.glassgo.platform.clients.domain.model.commands;

public record CreateClientCommand(
        Long userId,
        Long subscriptionId,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
