package com.demo.order;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

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

        // Legacy switch statement
        switch (order.getStatus()) {

            case "NEW":
                order.setStatus("PROCESSING");
                break;

            case "CANCELLED":
                return;

            default:
                break;
        }

        // Legacy callback style
        processor.processOrder(order,
                new LegacyOrderProcessor.OrderCallback() {

                    @Override
                    public void onSuccess(Order processedOrder) {

                        orders.add(processedOrder);

                        System.out.println(buildAuditMessage(processedOrder));

                    }

                    @Override
                    public void onFailure(Exception exception) {

                        System.out.println(
                                "Processing failed : "
                                        + exception.getMessage());

                    }

                });

    }

    /**
     * Return all orders.
     */
    public List<Order> getAllOrders() {

        // Traditional loop
        List<Order> result = new ArrayList<Order>();

        for (int i = 0; i < orders.size(); i++) {
            result.add(orders.get(i));
        }

        return result;

    }

    /**
     * Find an order.
     */
    public Order getOrder(Long orderId) {

        if (orderId == null) {
            return null;
        }

        for (Order order : orders) {

            if (orderId.equals(order.getOrderId())) {
                return order;
            }

        }

        return null;

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

        StringBuffer buffer = new StringBuffer();

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