package com.glassgo.platform.clients.domain.services;

import com.glassgo.platform.clients.domain.model.aggregates.Client;
import com.glassgo.platform.clients.domain.model.commands.CreateClientCommand;

import java.util.Optional;

public interface ClientCommandService {
    Optional<Client> handle(CreateClientCommand command);
}
