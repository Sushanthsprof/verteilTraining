package com.example.restaurantManagement.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

//@Entity
//@Table(name = "orders")
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", nullable = false)
//    @JsonIgnore
//    private Customer customer;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private OrderStatus status;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//    private List<OrderItem> items;
//
//    public Order() {}
//
//    public Order(Customer customer, OrderStatus status, List<OrderItem> items) {
//        this.customer = customer;
//        this.status = status;
//        this.items = items;
//    }
//
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//    public Customer getCustomer() { return customer; }
//    public void setCustomer(Customer customer) { this.customer = customer; }
//
//    public OrderStatus getStatus() { return status; }
//    public void setStatus(OrderStatus status) { this.status = status; }
//
//    public List<OrderItem> getItems() { return items; }
//    public void setItems(List<OrderItem> items) { this.items = items; }
//}

//package com.example.restaurantManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItem> items;
    public Order() {}

    public Order(Customer customer, OrderStatus status, List<OrderItem> items) {
        this.customer = customer;
        this.status = status;
        this.items = items;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

//    public double getTotalPrice() {
//        return items.stream().mapToDouble(item -> item.getMenu().getPrice() * item.getQuantity()).sum();
//    }
}
