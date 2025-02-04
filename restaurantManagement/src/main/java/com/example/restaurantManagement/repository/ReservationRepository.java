package com.example.restaurantManagement.repository;

import com.example.restaurantManagement.model.Reservation;
import com.example.restaurantManagement.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    public List<Reservation> findByReservationTime(String reservationTime);

    @Query("SELECT r FROM Reservation r WHERE r.reservationTime = :reservationTime AND r.status = :status")
    List<Reservation> findOccupiedReservationsAtTime(@Param("reservationTime") String reservationTime, @Param("status") ReservationStatus status);
}

