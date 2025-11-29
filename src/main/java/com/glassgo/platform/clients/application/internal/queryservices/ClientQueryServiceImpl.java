package com.glassgo.platform.clients.application.internal.queryservices;

import com.glassgo.platform.clients.domain.model.aggregates.Client;
import com.glassgo.platform.clients.domain.repositories.ClientRepository;
import com.glassgo.platform.clients.domain.services.ClientQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientRepository clientRepository;

    public ClientQueryServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> handle() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> handle(Long id) {
        return clientRepository.findById(id);
    }
}
