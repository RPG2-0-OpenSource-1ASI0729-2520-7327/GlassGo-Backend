package com.glassgo.platform.clients.domain.repositories;

import com.glassgo.platform.clients.domain.model.aggregates.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
}
