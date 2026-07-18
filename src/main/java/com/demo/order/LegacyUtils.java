package com.demo.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class LegacyUtils {

    // SRAO: Declared DateTimeFormatter as a static final field for efficiency and thread-safety.
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Legacy Vector usage.
     */
    public List<Order> createOrderVector(List<Order> orders) { // SRAO: Replaced Vector with List for modern collection usage.
        if (orders == null) {
            return new ArrayList<>(); // SRAO: Replaced Vector with ArrayList.
        }
        return new ArrayList<>(orders);
    }

    /**
     * Legacy Hashtable usage.
     */
    public HashMap<Long, Order> createOrderTable(List<Order> orders) {

        // SRAO: Replaced Hashtable with HashMap for better performance and modern API usage.
        if (orders == null) {
            return new HashMap<>();
        }
        // SRAO: Replaced for-loop with Stream API and Collectors.toMap for populating the map.
        return orders.stream()
                     .collect(Collectors.toMap(Order::getOrderId,
                                               order -> order,
                                               (existing, replacement) -> replacement,
                                               HashMap::new));
    }

    /**
     * Legacy Enumeration iteration.
     */
    public void printOrders(List<Order> orders) { // SRAO: Replaced Vector with List for modern collection usage.

        // SRAO: Replaced Enumeration with enhanced for loop for modern iteration.
        if (orders != null) {
            // SRAO: Replaced enhanced for-loop with Stream API for iteration.
            orders.stream().forEach(System.out::println);
        }
    }

    /**
     * Legacy Date formatting.
     */
    public String formatDate(Date date) {
        // SRAO: Replaced SimpleDateFormat with DateTimeFormatter for modern date formatting.
        return Optional.ofNullable(date)
                       .map(d -> {
                           LocalDateTime localDateTime = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                           // SRAO: Used the pre-defined static DateTimeFormatter for efficiency.
                           return DATE_TIME_FORMATTER.format(localDateTime);
                       })
                       .orElse("");
    }

    /**
     * Legacy Calendar usage.
     */
    public Date getNextProcessingDate() {

        // SRAO: Replaced Calendar with LocalDate for modern date manipulation.
        LocalDate futureDate = LocalDate.now().plusDays(2);
        return Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

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
        // SRAO: Encapsulate the unchecked cast in a private helper method to limit the scope of the @SuppressWarnings annotation.
        return uncheckedCastToListOfOrders(object);
    }

    @SuppressWarnings("unchecked")
    private List<Order> uncheckedCastToListOfOrders(Object object) {
        // SRAO: Add a runtime check for the raw List type to make the cast safer before performing the unchecked cast.
        if (!(object instanceof List)) {
            throw new ClassCastException("Object is not an instance of List and cannot be cast to List<Order>");
        }
        return (List<Order>) object;
    }

}
