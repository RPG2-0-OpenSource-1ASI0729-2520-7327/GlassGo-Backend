package com.glassgo.platform.payments.interfaces.rest.controllers;

import com.glassgo.platform.payments.domain.model.queries.GetAllSubscriptionPlansQuery;
import com.glassgo.platform.payments.domain.model.queries.GetSubscriptionPlanByIdQuery;
import com.glassgo.platform.payments.domain.model.services.SubscriptionPlanCommandService;
import com.glassgo.platform.payments.domain.model.services.SubscriptionPlanQueryService;
import com.glassgo.platform.payments.interfaces.rest.resources.CreateSubscriptionPlanResource;
import com.glassgo.platform.payments.interfaces.rest.resources.SubscriptionPlanResource;
import com.glassgo.platform.payments.interfaces.rest.transform.CreateSubscriptionPlanCommandFromResourceAssembler;
import com.glassgo.platform.payments.interfaces.rest.transform.SubscriptionPlanResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/payments/subscription-plans", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Subscription Plans", description = "Subscription Plans Management Endpoints")
public class SubscriptionPlansController {

    private final SubscriptionPlanCommandService subscriptionPlanCommandService;
    private final SubscriptionPlanQueryService subscriptionPlanQueryService;

    public SubscriptionPlansController(SubscriptionPlanCommandService subscriptionPlanCommandService, SubscriptionPlanQueryService subscriptionPlanQueryService) {
        this.subscriptionPlanCommandService = subscriptionPlanCommandService;
        this.subscriptionPlanQueryService = subscriptionPlanQueryService;
    }

    @PostMapping
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

    @GetMapping("/{subscriptionPlanId}")
    public ResponseEntity<SubscriptionPlanResource> getSubscriptionPlanById(@PathVariable Long subscriptionPlanId) {
        var getSubscriptionPlanByIdQuery = new GetSubscriptionPlanByIdQuery(subscriptionPlanId);
        var subscriptionPlan = subscriptionPlanQueryService.handle(getSubscriptionPlanByIdQuery);
        if (subscriptionPlan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var subscriptionPlanResource = SubscriptionPlanResourceFromEntityAssembler.toResourceFromEntity(subscriptionPlan.get());
        return ResponseEntity.ok(subscriptionPlanResource);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionPlanResource>> getAllSubscriptionPlans() {
        var getAllSubscriptionPlansQuery = new GetAllSubscriptionPlansQuery();
        var subscriptionPlans = subscriptionPlanQueryService.handle(getAllSubscriptionPlansQuery);
        var subscriptionPlanResources = subscriptionPlans.stream()
                .map(SubscriptionPlanResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subscriptionPlanResources);
    }
}
