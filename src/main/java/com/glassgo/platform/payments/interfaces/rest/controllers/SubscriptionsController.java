package com.glassgo.platform.payments.interfaces.rest.controllers;

import com.glassgo.platform.payments.domain.model.queries.GetAllSubscriptionsQuery;
import com.glassgo.platform.payments.domain.model.queries.GetSubscriptionByIdQuery;
import com.glassgo.platform.payments.domain.model.services.SubscriptionCommandService;
import com.glassgo.platform.payments.domain.model.services.SubscriptionQueryService;
import com.glassgo.platform.payments.interfaces.rest.resources.CreateSubscriptionResource;
import com.glassgo.platform.payments.interfaces.rest.resources.SubscriptionResource;
import com.glassgo.platform.payments.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.glassgo.platform.payments.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/payments/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Subscriptions", description = "Subscriptions Management Endpoints")
public class SubscriptionsController {

    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;

    public SubscriptionsController(SubscriptionCommandService subscriptionCommandService, SubscriptionQueryService subscriptionQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    @PostMapping
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

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResource> getSubscriptionById(@PathVariable Long subscriptionId) {
        var getSubscriptionByIdQuery = new GetSubscriptionByIdQuery(subscriptionId);
        var subscription = subscriptionQueryService.handle(getSubscriptionByIdQuery);
        if (subscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription.get());
        return ResponseEntity.ok(subscriptionResource);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResource>> getAllSubscriptions() {
        var getAllSubscriptionsQuery = new GetAllSubscriptionsQuery();
        var subscriptions = subscriptionQueryService.handle(getAllSubscriptionsQuery);
        var subscriptionResources = subscriptions.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subscriptionResources);
    }
}
