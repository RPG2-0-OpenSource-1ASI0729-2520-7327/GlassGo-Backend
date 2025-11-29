package com.glassgo.platform.clients.application.internal.commandservices;

import com.glassgo.platform.clients.domain.model.aggregates.Client;
import com.glassgo.platform.clients.domain.model.commands.CreateClientCommand;
import com.glassgo.platform.clients.domain.repositories.ClientRepository;
import com.glassgo.platform.clients.domain.services.ClientCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {

    private final ClientRepository clientRepository;

    public ClientCommandServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> handle(CreateClientCommand command) {
        if (clientRepository.findByEmail(command.email()).isPresent()) {
            throw new IllegalArgumentException("Client with email " + command.email() + " already exists");
        }
        var client = new Client(command);
        var createdClient = clientRepository.save(client);
        return Optional.of(createdClient);
    }
}
