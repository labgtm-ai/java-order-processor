package com.demo.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;

    private String customerName;

    private Double amount;

    private String status;

    private Date orderDate;

    private List<String> items;

    public Order() {
    }

    public Order(Long orderId,
                 String customerName,
                 Double amount,
                 String status,
                 Date orderDate,
                 List<String> items) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
        this.orderDate = orderDate;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        // SRAO: Replaced unchecked cast with pattern matching for instanceof for type safety and conciseness.
        if (!(obj instanceof Order other)) {
            return false;
        }

        // Retain strict class equality check as per original implementation.
        if (getClass() != other.getClass()) {
            return false;
        }

        if (orderId == null) {
            return other.orderId == null;
        }

        return orderId.equals(other.orderId);
    }

    @Override
    public int hashCode() {

        final int prime = 31;

        int result = 1;

        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());

        return result;
    }

    @Override
    public String toString() {

        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", items=" + items +
                '}';
    }

}
