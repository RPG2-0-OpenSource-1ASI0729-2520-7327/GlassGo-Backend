package com.glassgo.platform.clients.domain.services;

import com.glassgo.platform.clients.domain.model.aggregates.Client;

import java.util.List;
import java.util.Optional;

public interface ClientQueryService {
    List<Client> handle();
    Optional<Client> handle(Long id);
}
