package com.example.restaurantManagement.serviceLayer;

import com.example.restaurantManagement.model.Reservation;
import com.example.restaurantManagement.model.Tables;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    public List<Reservation> getAllReservations();
    public Reservation getReservationById(int id);
    public Reservation updateReservation(int id, Reservation updatedReservation);
    public void deleteReservation(int id);
    public Reservation saveReservation(Reservation reservation);
    public Reservation freeTables(int reservationId);
    //public List<String> getAvailableTables(String reservationTime);
    public Tables requestTable(String time);
}

