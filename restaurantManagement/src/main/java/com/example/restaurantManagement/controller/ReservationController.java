package com.example.restaurantManagement.controller;

import com.example.restaurantManagement.model.Tables;
import com.example.restaurantManagement.serviceLayer.ReservationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurantManagement.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rc")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/getAllReservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/getReservationById/{id}")
    public Reservation getReservationById(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping("/save")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @PutMapping("/updateReservation/{id}")
    public Reservation updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
    }

    @PutMapping("/freeTables/{id}")
    public Reservation freeTables(@PathVariable int id) {
        return reservationService.freeTables(id);
    }

    @GetMapping("/requestTable/{time}")
    public Tables requestTable(@PathVariable String time) {
        return reservationService.requestTable(time);
    }
}
