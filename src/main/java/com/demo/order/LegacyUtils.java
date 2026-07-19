package com.demo.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.time.Instant; // SRAO: Added for java.time API conversion
import java.time.LocalDateTime; // SRAO: Added for java.time API usage
import java.time.ZoneId; // SRAO: Added for java.time API usage
import java.time.format.DateTimeFormatter; // SRAO: Added for java.time API usage
import java.util.Collections; // SRAO: Added for Stream API null handling
import java.util.function.Function; // SRAO: Added for Stream API Collectors.toMap
import java.util.stream.Collectors; // SRAO: Added for Stream API collection operations

public class LegacyUtils {

    /**
     * Legacy Vector usage.
     */
    public List<Order> createOrderVector(List<Order> orders) {
        // SRAO: Replaced traditional for-loop with Stream API to copy elements into a new modifiable list.
        return Optional.ofNullable(orders)
                       .orElseGet(Collections::emptyList)
                       .stream()
                       .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Legacy Hashtable usage.
     */
    public HashMap<Long, Order> createOrderTable(List<Order> orders) {

        // SRAO: Replaced enhanced for-loop with Stream API to populate a HashMap.
        return Optional.ofNullable(orders)
                       .orElseGet(Collections::emptyList)
                       .stream()
                       .collect(Collectors.toMap(Order::getOrderId, Function.identity(), (oldValue, newValue) -> oldValue, HashMap::new));
    }

    /**
     * Legacy Enumeration iteration.
     */
    public void printOrders(List<Order> orders) { // SRAO: Replaced Vector with List and Enumeration with enhanced for-loop for modern iteration.

        // SRAO: Replaced enhanced for-loop with Stream API for concise iteration.
        orders.forEach(System.out::println);

    }

    /**
     * Legacy Date formatting.
     */
    public String formatDate(Date date) {

        // SRAO: Replaced SimpleDateFormat with DateTimeFormatter for modern date formatting.
        return Optional.ofNullable(date)
                .map(d -> LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()))
                .map(ldt -> ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .orElse("");

    }

    /**
     * Legacy Calendar usage.
     */
    public Date getNextProcessingDate() {

        // SRAO: Replaced Calendar with LocalDateTime for modern date manipulation.
        LocalDateTime nextProcessingDateTime = LocalDateTime.now().plusDays(2);
        return Date.from(nextProcessingDateTime.atZone(ZoneId.systemDefault()).toInstant());

    }

    /**
     * Raw collection example.
     */
    @SuppressWarnings("rawtypes")
    public List createRawList() {

        List list = new ArrayList();

        list.add("ORDER-1001");
        list.add(100);
        list.add(Boolean.TRUE);

        return list;

    }

    /**
     * Unchecked cast example.
     */
    public List<Order> castOrders(Object object) {
        // SRAO: Perform runtime type checks to ensure safe casting from Object to List<Order>.
        if (object instanceof List<?> rawList) {
            for (Object element : rawList) {
                if (!(element instanceof Order)) {
                    throw new ClassCastException("List contains non-Order elements.");
                }
            }
            // The list is verified to contain only Order objects.
            // The cast to List<Order> is safe at this point, though still unchecked by compiler.
            @SuppressWarnings("unchecked") // SRAO: Suppress warning after runtime verification.
            List<Order> typedList = (List<Order>) rawList;
            return typedList;
        } else {
            throw new ClassCastException("Object is not a List.");
        }
    }

}