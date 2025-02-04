package com.example.restaurantManagement.model;


import jakarta.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables table;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int numberOfPeople;
    private String reservationTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Transient
    private int customerId;

    public Tables getTable() { return table; }
    public void setTable(Tables table) { this.table = table; }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

}
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "reservation")
//public class Reservation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Tables> tables;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//
//    private int numberOfPeople;
//    private String reservationTime;
//    @Enumerated(EnumType.STRING) // Change to ORDINAL if needed
//    private ReservationStatus status;
//
//
//    @Transient
//    private int customerId;
//
//    public Reservation() {
//        this.status = ReservationStatus.OCCUPIED;
//    }
//
//    public List<Tables> getTables() {
//        return tables;
//    }
//
//    public void setTables(List<Tables> tables) {
//        this.tables = tables;
//    }
//
//
//}



