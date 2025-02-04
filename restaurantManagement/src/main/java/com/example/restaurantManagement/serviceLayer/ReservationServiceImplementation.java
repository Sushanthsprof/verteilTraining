package com.example.restaurantManagement.serviceLayer;
import com.example.restaurantManagement.model.Customer;
import com.example.restaurantManagement.model.Reservation;
import com.example.restaurantManagement.model.ReservationStatus;
import com.example.restaurantManagement.model.Tables;
import com.example.restaurantManagement.repository.ReservationRepository;
import com.example.restaurantManagement.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationServiceImplementation implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private TablesRepository tablesRepository;
    private static final List<String> ALL_TABLES = List.of("T1", "T2", "T3", "T4", "T5");
    @Autowired
    private CustomerService customerService;
    @Override
    public Reservation freeTables(int reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            if (reservation.getStatus() == ReservationStatus.FREED) {
                throw new RuntimeException("Reservation with ID " + reservationId + " is already freed.");
            }

            reservation.setTable(null);
            reservation.setStatus(ReservationStatus.FREED);
            return reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        Customer customer = customerService.getCustomer(reservation.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Customer not found for ID: " + reservation.getCustomerId());
        }
        Tables assignedTable = requestTable(reservation.getReservationTime());
        if (assignedTable == null) {
            throw new RuntimeException("No available tables for the requested time.");
        }
        reservation.setTable(assignedTable);
        reservation.setStatus(ReservationStatus.OCCUPIED);
        reservation.setCustomer(customer);
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(int id) {
        Optional<Reservation> reservation=reservationRepository.findById(id);
        if(reservation.isPresent()){
            return reservation.get();
        }
        return null;
    }
    @Override
    public Reservation updateReservation(int id,Reservation updatedReservation) {
        if (reservationRepository.existsById(id)) {
            updatedReservation.setId(id);
            return reservationRepository.save(updatedReservation);
        }
        return null;
    }
    @Override
    public void deleteReservation(int id) {
        Optional<Reservation> optionalReservation=reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            Reservation reservation=optionalReservation.get();
            System.out.println("Reservation with id ="+id+" has been deleted");
            reservationRepository.deleteById(id);
            return;
        }
        System.out.println("No reservation with id ="+id +"has been found");
    }

//    @Override
//    public Tables requestTable(String reservationTime) {
//        // Fetch all occupied reservations at the given time
//        List<Reservation> occupiedReservations = reservationRepository
//                .findOccupiedReservationsAtTime(reservationTime, ReservationStatus.OCCUPIED);
//
//        // Log the occupied reservations
//        System.out.println("Occupied Reservations: " + occupiedReservations);
//
//        // Extract occupied table IDs
//        Set<Integer> occupiedTableIds = occupiedReservations.stream()
//                .map(res -> res.getTable().getId())
//                .collect(Collectors.toSet());
//
//        // Log the occupied table IDs
//        System.out.println("Occupied Table IDs: " + occupiedTableIds);
//
//        // Fetch all tables
//        List<Tables> allTables = tablesRepository.findAll();
//
//        System.out.println("All Tables: " + allTables);
//
//        for (Tables table : allTables) {
//            if (!occupiedTableIds.contains(table.getId())) {
//                System.out.println("Available Table: " + table);
//                return table;
//            }
//        }
//
//        System.out.println("No available tables found.");
//        return null;
//    }

    @Override
    public Tables requestTable(String reservationTime) {
        List<Reservation> occupiedReservations = reservationRepository
                .findOccupiedReservationsAtTime(reservationTime, ReservationStatus.OCCUPIED);
        System.out.println("Occupied Reservations: " + occupiedReservations);
        Set<Integer> occupiedTableIds = occupiedReservations.stream()
                .filter(res -> res.getTable() != null)  // Ensure table is not null
                .map(res -> res.getTable().getId())
                .collect(Collectors.toSet());
        System.out.println("Occupied Table IDs: " + occupiedTableIds);
        List<Tables> allTables = tablesRepository.findAll();
        System.out.println("All Tables: " + allTables);
        for (Tables table : allTables) {
            if (!occupiedTableIds.contains(table.getId())) {
                System.out.println("Available Table: " + table);
                return table;
            }
        }
        System.out.println("No available tables found.");
        return null;
    }


//    Override
//    public List<String> getAvailableTables(String reservationTime) {
//        List<Reservation> occupiedReservations = reservationRepository
//                .findOccupiedReservationsAtTime(reservationTime, ReservationStatus.OCCUPIED);
//        Set<String> occupiedTables = occupiedReservations.stream()
//                .flatMap(res -> res.getTableNumbers().stream())
//                .collect(Collectors.toSet());
//        List<String> availableTables = new ArrayList<>(ALL_TABLES);
//        availableTables.removeAll(occupiedTables);
//        return availableTables;
//    }

}

