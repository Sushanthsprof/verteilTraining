package com.example.restaurantManagement.dto;
import java.util.List;

public class OrderRequest {
    private int customerId;
    private String status;
    private List<OrderItemRequest> items;
    OrderRequest(){}
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
