package com.glassgo.platform.payments.interfaces.rest.controllers;

import com.glassgo.platform.payments.domain.model.queries.*;
import com.glassgo.platform.payments.domain.model.services.*;
import com.glassgo.platform.payments.interfaces.rest.resources.*;
import com.glassgo.platform.payments.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PaymentsController handles payment-related REST endpoints in the DDD architecture.
 * It provides operations for managing transactions, subscriptions, subscription plans,
 * and payment gateways.
 */
@RestController
@RequestMapping(value = "/api/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Payments Management Endpoints")
public class PaymentsController {

    private final TransactionCommandService transactionCommandService;
    private final TransactionQueryService transactionQueryService;
    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;
    private final SubscriptionPlanCommandService subscriptionPlanCommandService;
    private final SubscriptionPlanQueryService subscriptionPlanQueryService;
    private final PaymentGatewayCommandService paymentGatewayCommandService;
    private final PaymentGatewayQueryService paymentGatewayQueryService;

    public PaymentsController(TransactionCommandService transactionCommandService, TransactionQueryService transactionQueryService,
                              SubscriptionCommandService subscriptionCommandService, SubscriptionQueryService subscriptionQueryService,
                              SubscriptionPlanCommandService subscriptionPlanCommandService, SubscriptionPlanQueryService subscriptionPlanQueryService,
                              PaymentGatewayCommandService paymentGatewayCommandService, PaymentGatewayQueryService paymentGatewayQueryService) {
        this.transactionCommandService = transactionCommandService;
        this.transactionQueryService = transactionQueryService;
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
        this.subscriptionPlanCommandService = subscriptionPlanCommandService;
        this.subscriptionPlanQueryService = subscriptionPlanQueryService;
        this.paymentGatewayCommandService = paymentGatewayCommandService;
        this.paymentGatewayQueryService = paymentGatewayQueryService;
    }

    // Transaction Endpoints
    @Operation(summary = "Create a new transaction")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transaction created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/transactions")
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

    @Operation(summary = "Get a transaction by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transaction found"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @GetMapping("/transactions/{transactionId}")
    public ResponseEntity<TransactionResource> getTransactionById(@PathVariable Long transactionId) {
        var getTransactionByIdQuery = new GetTransactionByIdQuery(transactionId);
        var transaction = transactionQueryService.handle(getTransactionByIdQuery);
        if (transaction.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(transaction.get());
        return ResponseEntity.ok(transactionResource);
    }

    @Operation(summary = "Get all transactions")
    @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully")
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResource>> getAllTransactions() {
        var getAllTransactionsQuery = new GetAllTransactionsQuery();
        var transactions = transactionQueryService.handle(getAllTransactionsQuery);
        var transactionResources = transactions.stream()
                .map(TransactionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionResources);
    }

    // Subscription Endpoints
    @Operation(summary = "Create a new subscription")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Subscription created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/subscriptions")
    public ResponseEntity<SubscriptionResource> createSubscription(@RequestBody CreateSubscriptionResource resource) {
        var createSubscriptionCommand = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource);
        var subscriptionId = subscriptionCommandService.handle(createSubscriptionCommand);
        if (subscriptionId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getSubscriptionByIdQuery = new GetSubscriptionByIdQuery(subscriptionId);
        var subscription = subscriptionQueryService.handle(getSubscriptionByIdQuery);
        if (subscription.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription.get());
        return new ResponseEntity<>(subscriptionResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a subscription by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Subscription found"),
        @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    @GetMapping("/subscriptions/{subscriptionId}")
    public ResponseEntity<SubscriptionResource> getSubscriptionById(@PathVariable Long subscriptionId) {
        var getSubscriptionByIdQuery = new GetSubscriptionByIdQuery(subscriptionId);
        var subscription = subscriptionQueryService.handle(getSubscriptionByIdQuery);
        if (subscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription.get());
        return ResponseEntity.ok(subscriptionResource);
    }

    @Operation(summary = "Get all subscriptions")
    @ApiResponse(responseCode = "200", description = "Subscriptions retrieved successfully")
    @GetMapping("/subscriptions")
    public ResponseEntity<List<SubscriptionResource>> getAllSubscriptions() {
        var getAllSubscriptionsQuery = new GetAllSubscriptionsQuery();
        var subscriptions = subscriptionQueryService.handle(getAllSubscriptionsQuery);
        var subscriptionResources = subscriptions.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subscriptionResources);
    }

    // SubscriptionPlan Endpoints
    @Operation(summary = "Create a new subscription plan")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Subscription plan created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/subscription-plans")
    public ResponseEntity<SubscriptionPlanResource> createSubscriptionPlan(@RequestBody CreateSubscriptionPlanResource resource) {
        var createSubscriptionPlanCommand = CreateSubscriptionPlanCommandFromResourceAssembler.toCommandFromResource(resource);
        var subscriptionPlanId = subscriptionPlanCommandService.handle(createSubscriptionPlanCommand);
        if (subscriptionPlanId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getSubscriptionPlanByIdQuery = new GetSubscriptionPlanByIdQuery(subscriptionPlanId);
        var subscriptionPlan = subscriptionPlanQueryService.handle(getSubscriptionPlanByIdQuery);
        if (subscriptionPlan.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var subscriptionPlanResource = SubscriptionPlanResourceFromEntityAssembler.toResourceFromEntity(subscriptionPlan.get());
        return new ResponseEntity<>(subscriptionPlanResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a subscription plan by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Subscription plan found"),
        @ApiResponse(responseCode = "404", description = "Subscription plan not found")
    })
    @GetMapping("/subscription-plans/{subscriptionPlanId}")
    public ResponseEntity<SubscriptionPlanResource> getSubscriptionPlanById(@PathVariable Long subscriptionPlanId) {
        var getSubscriptionPlanByIdQuery = new GetSubscriptionPlanByIdQuery(subscriptionPlanId);
        var subscriptionPlan = subscriptionPlanQueryService.handle(getSubscriptionPlanByIdQuery);
        if (subscriptionPlan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var subscriptionPlanResource = SubscriptionPlanResourceFromEntityAssembler.toResourceFromEntity(subscriptionPlan.get());
        return ResponseEntity.ok(subscriptionPlanResource);
    }

    @Operation(summary = "Get all subscription plans")
    @ApiResponse(responseCode = "200", description = "Subscription plans retrieved successfully")
    @GetMapping("/subscription-plans")
    public ResponseEntity<List<SubscriptionPlanResource>> getAllSubscriptionPlans() {
        var getAllSubscriptionPlansQuery = new GetAllSubscriptionPlansQuery();
        var subscriptionPlans = subscriptionPlanQueryService.handle(getAllSubscriptionPlansQuery);
        var subscriptionPlanResources = subscriptionPlans.stream()
                .map(SubscriptionPlanResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subscriptionPlanResources);
    }

    // PaymentGateway Endpoints
    @Operation(summary = "Create a new payment gateway")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Payment gateway created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/payment-gateways")
    public ResponseEntity<PaymentGatewayResource> createPaymentGateway(@RequestBody CreatePaymentGatewayResource resource) {
        var createPaymentGatewayCommand = CreatePaymentGatewayCommandFromResourceAssembler.toCommandFromResource(resource);
        var paymentGatewayId = paymentGatewayCommandService.handle(createPaymentGatewayCommand);
        if (paymentGatewayId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getPaymentGatewayByIdQuery = new GetPaymentGatewayByIdQuery(paymentGatewayId);
        var paymentGateway = paymentGatewayQueryService.handle(getPaymentGatewayByIdQuery);
        if (paymentGateway.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var paymentGatewayResource = PaymentGatewayResourceFromEntityAssembler.toResourceFromEntity(paymentGateway.get());
        return new ResponseEntity<>(paymentGatewayResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a payment gateway by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment gateway found"),
        @ApiResponse(responseCode = "404", description = "Payment gateway not found")
    })
    @GetMapping("/payment-gateways/{paymentGatewayId}")
    public ResponseEntity<PaymentGatewayResource> getPaymentGatewayById(@PathVariable Long paymentGatewayId) {
        var getPaymentGatewayByIdQuery = new GetPaymentGatewayByIdQuery(paymentGatewayId);
        var paymentGateway = paymentGatewayQueryService.handle(getPaymentGatewayByIdQuery);
        if (paymentGateway.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var paymentGatewayResource = PaymentGatewayResourceFromEntityAssembler.toResourceFromEntity(paymentGateway.get());
        return ResponseEntity.ok(paymentGatewayResource);
    }

    @Operation(summary = "Get all payment gateways")
    @ApiResponse(responseCode = "200", description = "Payment gateways retrieved successfully")
    @GetMapping("/payment-gateways")
    public ResponseEntity<List<PaymentGatewayResource>> getAllPaymentGateways() {
        var getAllPaymentGatewaysQuery = new GetAllPaymentGatewaysQuery();
        var paymentGateways = paymentGatewayQueryService.handle(getAllPaymentGatewaysQuery);
        var paymentGatewayResources = paymentGateways.stream()
                .map(PaymentGatewayResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentGatewayResources);
    }
}
