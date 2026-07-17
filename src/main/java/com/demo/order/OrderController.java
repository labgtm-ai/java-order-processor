package com.demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

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

        Order order = orderService.getOrder(orderId);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);

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