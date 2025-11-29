package com.glassgo.platform.payments.interfaces.rest.controllers;

import com.glassgo.platform.payments.domain.model.queries.GetAllTransactionsQuery;
import com.glassgo.platform.payments.domain.model.queries.GetTransactionByIdQuery;
import com.glassgo.platform.payments.domain.model.services.TransactionCommandService;
import com.glassgo.platform.payments.domain.model.services.TransactionQueryService;
import com.glassgo.platform.payments.interfaces.rest.resources.CreateTransactionResource;
import com.glassgo.platform.payments.interfaces.rest.resources.TransactionResource;
import com.glassgo.platform.payments.interfaces.rest.transform.CreateTransactionCommandFromResourceAssembler;
import com.glassgo.platform.payments.interfaces.rest.transform.TransactionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/payments/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transactions", description = "Transactions Management Endpoints")
public class TransactionsController {

    private final TransactionCommandService transactionCommandService;
    private final TransactionQueryService transactionQueryService;

    public TransactionsController(TransactionCommandService transactionCommandService, TransactionQueryService transactionQueryService) {
        this.transactionCommandService = transactionCommandService;
        this.transactionQueryService = transactionQueryService;
    }

    @PostMapping
    public ResponseEntity<TransactionResource> createTransaction(@RequestBody CreateTransactionResource resource) {
        var createTransactionCommand = CreateTransactionCommandFromResourceAssembler.toCommandFromResource(resource);
        var transactionId = transactionCommandService.handle(createTransactionCommand);
        if (transactionId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getTransactionByIdQuery = new GetTransactionByIdQuery(transactionId);
        var transaction = transactionQueryService.handle(getTransactionByIdQuery);
        if (transaction.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(transaction.get());
        return new ResponseEntity<>(transactionResource, HttpStatus.CREATED);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResource> getTransactionById(@PathVariable Long transactionId) {
        var getTransactionByIdQuery = new GetTransactionByIdQuery(transactionId);
        var transaction = transactionQueryService.handle(getTransactionByIdQuery);
        if (transaction.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(transaction.get());
        return ResponseEntity.ok(transactionResource);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResource>> getAllTransactions() {
        var getAllTransactionsQuery = new GetAllTransactionsQuery();
        var transactions = transactionQueryService.handle(getAllTransactionsQuery);
        var transactionResources = transactions.stream()
                .map(TransactionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionResources);
    }
}
