package com.rutusoft.camunda.pojo;


import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId = "checkout-generated-" + UUID.randomUUID().toString();
    private Customer customer;
    private List<Item> items = new ArrayList<>();
    private Double totalAmount;
    private String status;

    public Order(String orderId, Customer customer, List<Item> items, Double totalAmount, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
