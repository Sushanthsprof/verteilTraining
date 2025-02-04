package com.example.restaurantManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

//@Entity
//@Table(name = "order_items")
//public class OrderItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(nullable = false)
//    private double price;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", nullable = false)
//    @JsonIgnore
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menu_id", nullable = false)
//    private Menu menu;
//
//    @Column(nullable = false)
//    private int quantity;
//
//    public OrderItem() {}
//
//    public OrderItem(Order order, Menu menu, int quantity) {
//        this.order = order;
//        this.menu = menu;
//        this.quantity = quantity;
//    }
//
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public Order getOrder() { return order; }
//    public void setOrder(Order order) { this.order = order; }
//
//    public Menu getMenu() { return menu; }
//    public void setMenu(Menu menu) { this.menu = menu; }
//
//    public int getQuantity() { return quantity; }
//    public void setQuantity(int quantity) { this.quantity = quantity; }
//}
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private int quantity;
    public OrderItem() {}

    public OrderItem(Order order, Menu menu, int quantity) {
        this.order = order;
        this.menu = menu;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}