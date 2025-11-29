package com.glassgo.platform.clients.interfaces.rest;

import com.glassgo.platform.clients.domain.services.ClientCommandService;
import com.glassgo.platform.clients.domain.services.ClientQueryService;
import com.glassgo.platform.clients.interfaces.rest.resources.ClientResource;
import com.glassgo.platform.clients.interfaces.rest.resources.CreateClientResource;
import com.glassgo.platform.clients.interfaces.rest.transform.ClientResourceFromEntityAssembler;
import com.glassgo.platform.clients.interfaces.rest.transform.CreateClientCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Clients", description = "Clients Management Endpoints")
public class ClientController {

    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    public ClientController(ClientCommandService clientCommandService, ClientQueryService clientQueryService) {
        this.clientCommandService = clientCommandService;
        this.clientQueryService = clientQueryService;
    }

    @PostMapping
    public ResponseEntity<ClientResource> createClient(@RequestBody CreateClientResource resource) {
        var createClientCommand = CreateClientCommandFromResourceAssembler.toCommandFromResource(resource);
        var client = clientCommandService.handle(createClientCommand);
        return client.map(value -> new ResponseEntity<>(ClientResourceFromEntityAssembler.toResourceFromEntity(value), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<ClientResource>> getAllClients() {
        var clients = clientQueryService.handle();
        var clientResources = clients.stream()
                .map(ClientResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResource> getClientById(@PathVariable Long id) {
        var client = clientQueryService.handle(id);
        return client.map(value -> ResponseEntity.ok(ClientResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
