package com.demo.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService; // SRAO: Replaced field injection with constructor injection.

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Returns all processed orders.
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(orderService.getAllOrders());

    }

    /**
     * Returns a single order.
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(
            @PathVariable("orderId") Long orderId) {

        // SRAO: Replaced explicit null check with Optional for better null handling.
        return Optional.ofNullable(orderService.getOrder(orderId))
                       .map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * Process a new order.
     */
    @PostMapping("/process")
    public ResponseEntity<String> processOrder(
            @RequestBody Order order) {

        orderService.processOrder(order);

        return ResponseEntity.ok(
                "Order submitted successfully.");

    }

    /**
     * Delete an order.
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable("orderId") Long orderId) {

        boolean deleted = orderService.deleteOrder(orderId);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                "Order deleted successfully.");

    }

}