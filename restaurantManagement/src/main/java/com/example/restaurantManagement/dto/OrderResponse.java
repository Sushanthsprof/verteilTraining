package com.example.restaurantManagement.dto;

import com.example.restaurantManagement.model.OrderStatus;
import java.util.List;

public class OrderResponse {
    private int id;
    private int customerId;
    private String customerName;
    private String customerContact;
    private OrderStatus status;
    private List<OrderItemResponse> items;
    private double totalPrice;

    public OrderResponse() {}

    public OrderResponse(int id, int customerId, String customerName, String customerContact, OrderStatus status, List<OrderItemResponse> items, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.status = status;
        this.items = items;
        this.totalPrice = totalPrice;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerContact() { return customerContact; }
    public void setCustomerContact(String customerContact) { this.customerContact = customerContact; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public List<OrderItemResponse> getItems() { return items; }
    public void setItems(List<OrderItemResponse> items) { this.items = items; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
