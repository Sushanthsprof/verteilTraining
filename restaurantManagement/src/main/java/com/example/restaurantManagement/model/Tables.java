package com.example.restaurantManagement.model;
import jakarta.persistence.*;
@Entity
@Table(name = "tables")
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    Tables(){}
    public Tables(String tableNumber, int capacity) {
        this.tableNumber = tableNumber;
        this.capacity = capacity;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private String tableNumber;
    private int capacity;

}

