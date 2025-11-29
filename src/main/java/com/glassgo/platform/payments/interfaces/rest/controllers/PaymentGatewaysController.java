package com.glassgo.platform.payments.interfaces.rest.controllers;

import com.glassgo.platform.payments.domain.model.queries.GetAllPaymentGatewaysQuery;
import com.glassgo.platform.payments.domain.model.queries.GetPaymentGatewayByIdQuery;
import com.glassgo.platform.payments.domain.model.services.PaymentGatewayCommandService;
import com.glassgo.platform.payments.domain.model.services.PaymentGatewayQueryService;
import com.glassgo.platform.payments.interfaces.rest.resources.CreatePaymentGatewayResource;
import com.glassgo.platform.payments.interfaces.rest.resources.PaymentGatewayResource;
import com.glassgo.platform.payments.interfaces.rest.transform.CreatePaymentGatewayCommandFromResourceAssembler;
import com.glassgo.platform.payments.interfaces.rest.transform.PaymentGatewayResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/payments/payment-gateways", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payment Gateways", description = "Payment Gateways Management Endpoints")
public class PaymentGatewaysController {

    private final PaymentGatewayCommandService paymentGatewayCommandService;
    private final PaymentGatewayQueryService paymentGatewayQueryService;

    public PaymentGatewaysController(PaymentGatewayCommandService paymentGatewayCommandService, PaymentGatewayQueryService paymentGatewayQueryService) {
        this.paymentGatewayCommandService = paymentGatewayCommandService;
        this.paymentGatewayQueryService = paymentGatewayQueryService;
    }

    @PostMapping
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

    @GetMapping("/{paymentGatewayId}")
    public ResponseEntity<PaymentGatewayResource> getPaymentGatewayById(@PathVariable Long paymentGatewayId) {
        var getPaymentGatewayByIdQuery = new GetPaymentGatewayByIdQuery(paymentGatewayId);
        var paymentGateway = paymentGatewayQueryService.handle(getPaymentGatewayByIdQuery);
        if (paymentGateway.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var paymentGatewayResource = PaymentGatewayResourceFromEntityAssembler.toResourceFromEntity(paymentGateway.get());
        return ResponseEntity.ok(paymentGatewayResource);
    }

    @GetMapping
    public ResponseEntity<List<PaymentGatewayResource>> getAllPaymentGateways() {
        var getAllPaymentGatewaysQuery = new GetAllPaymentGatewaysQuery();
        var paymentGateways = paymentGatewayQueryService.handle(getAllPaymentGatewaysQuery);
        var paymentGatewayResources = paymentGateways.stream()
                .map(PaymentGatewayResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentGatewayResources);
    }
}
