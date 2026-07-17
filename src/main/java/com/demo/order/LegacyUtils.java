package com.demo.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class LegacyUtils {

    /**
     * Legacy Vector usage.
     */
    public Vector<Order> createOrderVector(List<Order> orders) {

        Vector<Order> vector = new Vector<Order>();

        if (orders != null) {

            for (int i = 0; i < orders.size(); i++) {
                vector.add(orders.get(i));
            }

        }

        return vector;
    }

    /**
     * Legacy Hashtable usage.
     */
    public Hashtable<Long, Order> createOrderTable(List<Order> orders) {

        Hashtable<Long, Order> table =
                new Hashtable<Long, Order>();

        if (orders != null) {

            for (Order order : orders) {
                table.put(order.getOrderId(), order);
            }

        }

        return table;
    }

    /**
     * Legacy Enumeration iteration.
     */
    public void printOrders(Vector<Order> orders) {

        Enumeration<Order> enumeration = orders.elements();

        while (enumeration.hasMoreElements()) {

            Order order = enumeration.nextElement();

            System.out.println(order);

        }

    }

    /**
     * Legacy Date formatting.
     */
    public String formatDate(Date date) {

        if (date == null) {
            return "";
        }

        SimpleDateFormat formatter =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(date);

    }

    /**
     * Legacy Calendar usage.
     */
    public Date getNextProcessingDate() {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, 2);

        return calendar.getTime();

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
    @SuppressWarnings("unchecked")
    public List<Order> castOrders(Object object) {

        return (List<Order>) object;

    }

}