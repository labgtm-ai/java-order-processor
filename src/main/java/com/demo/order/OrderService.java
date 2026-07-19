package com.demo.order;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    // SRAO: Replaced System.out.println with a logging framework for better control and flexibility.
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final List<Order> orders = new ArrayList<Order>();

    private final LegacyOrderProcessor processor =
            new LegacyOrderProcessor();

    private final LegacyUtils legacyUtils =
            new LegacyUtils();

    /**
     * Process an order.
     */
    public void processOrder(final Order order) {

        // Explicit null check
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        // Manual object initialization
        if (order.getOrderDate() == null) {
            // SRAO: Replaced legacy Date instantiation with java.time.Instant and conversion to java.util.Date.
            order.setOrderDate(Date.from(Instant.now()));
        }

        if (order.getStatus() == null) {
            order.setStatus("NEW");
        }

        // SRAO: Replaced traditional switch statement with a switch expression.
        // Handle CANCELLED status early as it causes method exit.
        if ("CANCELLED".equals(order.getStatus())) {
            return;
        }

        // Use a switch expression to determine the new status if applicable.
        String statusToSet = switch (order.getStatus()) {
            case "NEW" -> "PROCESSING";
            // For other statuses (not NEW, not CANCELLED), the status remains unchanged.
            default -> order.getStatus(); // Yield the current status
        };

        // Only update status if it has actually changed.
        if (!order.getStatus().equals(statusToSet)) {
            order.setStatus(statusToSet);
        }

        // Legacy callback style
        processor.processOrder(order,
                new LegacyOrderProcessor.OrderCallback() {

                    @Override
                    public void onSuccess(Order processedOrder) {

                        orders.add(processedOrder);

                        logger.info(buildAuditMessage(processedOrder));

                    }

                    @Override
                    public void onFailure(Exception exception) {

                        logger.error("Processing failed : {}", exception.getMessage(), exception);

                    }

                });

    }

    /**
     * Return all orders.
     */
    public List<Order> getAllOrders() {
        // SRAO: Replaced traditional for-loop with Stream API to create a modifiable list.
        return orders.stream().collect(Collectors.toCollection(ArrayList::new));

    }

    /**
     * Find an order.
     */
    public Order getOrder(Long orderId) {

        if (orderId == null) {
            return null;
        }

        // SRAO: Replaced traditional for-each loop with Stream API for finding an element.
        return orders.stream()
                     .filter(order -> orderId.equals(order.getOrderId()))
                     .findFirst()
                     .orElse(null);

    }

    /**
     * Delete order.
     */
    public boolean deleteOrder(Long orderId) {

        Order order = getOrder(orderId);

        if (order == null) {
            return false;
        }

        return orders.remove(order);

    }

    /**
     * Raw collection example.
     */
    @SuppressWarnings({"rawtypes","unchecked"})
    public List getLegacyOrders() {

        List list = new ArrayList();

        list.addAll(orders);

        return list;

    }

    /**
     * Unchecked cast example.
     */
    public List<Order> convert(Object object) {
        // SRAO: Replaced unchecked cast with safe type checking using instanceof pattern matching and element-wise casting.
        if (!(object instanceof List<?>)) {
            throw new ClassCastException("Object cannot be cast to List");
        }

        // SRAO: Replaced traditional for-each loop with Stream API for element-wise casting and collection.
        return ((List<?>) object).stream()
                                 .map(item -> {
                                     if (item instanceof Order order) {
                                         return order;
                                     } else {
                                         throw new ClassCastException("Element in list is not an Order: " + (item != null ? item.getClass().getName() : "null"));
                                     }
                                 })
                                 .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Legacy StringBuffer usage.
     */
    private String buildAuditMessage(Order order) {

        // SRAO: Replaced StringBuffer with StringBuilder for better performance in a non-thread-safe context.
        StringBuilder buffer = new StringBuilder();

        buffer.append("Order Id : ");

        buffer.append(order.getOrderId());

        buffer.append(" | Customer : ");

        buffer.append(order.getCustomerName());

        buffer.append(" | Status : ");

        buffer.append(order.getStatus());

        return buffer.toString();

    }

    /**
     * Demonstrates usage of legacy utility APIs.
     */
    public void demonstrateLegacyUtilities() {

        legacyUtils.createOrderVector(orders);

        legacyUtils.createOrderTable(orders);

        // SRAO: Replaced legacy Date instantiation with java.time.Instant and conversion to java.util.Date.
        legacyUtils.formatDate(Date.from(Instant.now()));

        legacyUtils.getNextProcessingDate();

    }

}
