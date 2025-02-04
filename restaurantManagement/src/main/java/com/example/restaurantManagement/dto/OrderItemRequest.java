package com.example.restaurantManagement.dto;

public class OrderItemRequest {
    private int menuId;
    private int quantity;
    public int getMenuId() {
        return menuId;
    }
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}