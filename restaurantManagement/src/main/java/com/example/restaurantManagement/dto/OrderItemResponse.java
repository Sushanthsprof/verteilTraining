package com.example.restaurantManagement.dto;

public class OrderItemResponse {
    private String itemName;
    private int quantity;
    private double unitPrice;
    private double subtotal;
    public OrderItemResponse() {}
    public OrderItemResponse(String itemName, int quantity, double unitPrice, double subtotal) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
