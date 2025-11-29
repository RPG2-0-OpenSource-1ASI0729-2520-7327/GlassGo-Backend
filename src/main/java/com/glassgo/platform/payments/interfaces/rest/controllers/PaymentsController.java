package com.glassgo.platform.payments.interfaces.rest.controllers;

import com.glassgo.platform.payments.domain.model.queries.*;
import com.glassgo.platform.payments.domain.model.services.*;
import com.glassgo.platform.payments.interfaces.rest.resources.*;
import com.glassgo.platform.payments.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
