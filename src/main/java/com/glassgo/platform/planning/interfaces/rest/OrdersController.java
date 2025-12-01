package com.glassgo.platform.planning.interfaces.rest;

import com.glassgo.platform.planning.application.internal.commandservices.CreateOrderHandler;
import com.glassgo.platform.planning.application.internal.queryservices.OrderQueryService;
import com.glassgo.platform.planning.domain.model.aggregates.Order;
import com.glassgo.platform.planning.domain.model.commands.SubmitOrderCommand;
import com.glassgo.platform.planning.interfaces.rest.resources.CreateOrderResource;
import com.glassgo.platform.planning.interfaces.rest.resources.OrderResource;
import com.glassgo.platform.planning.interfaces.rest.transform.CreateOrderCommandFromResourceAssembler;
import com.glassgo.platform.planning.interfaces.rest.transform.OrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing orders in the planning domain.
 * Provides endpoints for creating and retrieving orders.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/orders", produces = "application/json")
@Tag(name = "Orders", description = "Order Management Endpoints")
public class OrdersController {

    private final CreateOrderHandler createOrderHandler;
    private final OrderQueryService orderQueryService;

    public OrdersController(CreateOrderHandler createOrderHandler, OrderQueryService orderQueryService) {
        this.createOrderHandler = createOrderHandler;
        this.orderQueryService = orderQueryService;
    }

    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Order created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource resource) {
        try {
            var createOrderCommand = CreateOrderCommandFromResourceAssembler.toCommandFromResource(resource);
            var order = createOrderHandler.handle(createOrderCommand);
            var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(orderResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get all orders")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    @GetMapping
    public ResponseEntity<List<OrderResource>> getAllOrders() {
        var orders = orderQueryService.getAllOrders();
        var orderResources = orders.stream()
            .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(orderResources);
    }

    @Operation(summary = "Get order by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResource> getOrderById(@PathVariable Long orderId) {
        var orderOptional = orderQueryService.getOrderById(orderId);
        if (orderOptional.isPresent()) {
            var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(orderOptional.get());
            return ResponseEntity.ok(orderResource);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get order by order number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<OrderResource> getOrderByOrderNumber(@PathVariable String orderNumber) {
        var orderOptional = orderQueryService.getOrderByOrderNumber(orderNumber);
        if (orderOptional.isPresent()) {
            var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(orderOptional.get());
            return ResponseEntity.ok(orderResource);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Submit an order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order submitted successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "400", description = "Order cannot be submitted")
    })
    @PostMapping("/{orderId}/submit")
    public ResponseEntity<OrderResource> submitOrder(@PathVariable Long orderId) {
        try {
            var submitCommand = new SubmitOrderCommand(orderId);
            var order = createOrderHandler.handle(submitCommand);
            var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order);
            return ResponseEntity.ok(orderResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get orders by status")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResource>> getOrdersByStatus(@PathVariable String status) {
        try {
            var orderStatus = Order.OrderStatus.valueOf(status.toUpperCase());
            var orders = orderQueryService.getOrdersByStatus(orderStatus);
            var orderResources = orders.stream()
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
            return ResponseEntity.ok(orderResources);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get active orders")
    @ApiResponse(responseCode = "200", description = "Active orders retrieved successfully")
    @GetMapping("/active")
    public ResponseEntity<List<OrderResource>> getActiveOrders() {
        var orders = orderQueryService.getActiveOrders();
        var orderResources = orders.stream()
            .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(orderResources);
    }
}
