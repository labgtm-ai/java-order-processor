package com.demo.order;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    // SRAO: Replaced System.out.println with SLF4J logger
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
            order.setOrderDate(new Date());
        }

        if (order.getStatus() == null) {
            order.setStatus("NEW");
        }

        // SRAO: Replaced traditional switch statement with switch expression (statement form)
        switch (order.getStatus()) {
            case "NEW" -> order.setStatus("PROCESSING");
            case "CANCELLED" -> {
                return;
            }
            default -> {
            }
        }

        // Legacy callback style
        processor.processOrder(order,
                new LegacyOrderProcessor.OrderCallback() {

                    @Override
                    public void onSuccess(Order processedOrder) {

                        orders.add(processedOrder);

                        // SRAO: Replaced System.out.println with SLF4J logger
                        logger.info(buildAuditMessage(processedOrder));

                    }

                    @Override
                    public void onFailure(Exception exception) {

                        // SRAO: Replaced System.out.println with SLF4J logger
                        logger.error("Processing failed : {}", exception.getMessage(), exception);

                    }

                });

    }

    /**
     * Return all orders.
     */
    public List<Order> getAllOrders() {

        // SRAO: Replaced traditional for-loop with Stream API
        return orders.stream().collect(Collectors.toList());

    }

    /**
     * Find an order.
     */
    public Order getOrder(Long orderId) {

        if (orderId == null) {
            return null;
        }

        // SRAO: Replaced enhanced for-loop with Stream API
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
    @SuppressWarnings("unchecked")
    public List<Order> convert(Object object) {

        return (List<Order>) object;

    }

    /**
     * Legacy StringBuffer usage.
     */
    private String buildAuditMessage(Order order) {

        StringBuilder buffer = new StringBuilder(); // SRAO: Replaced StringBuffer with StringBuilder for performance

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

        legacyUtils.formatDate(new Date());

        legacyUtils.getNextProcessingDate();

    }

}