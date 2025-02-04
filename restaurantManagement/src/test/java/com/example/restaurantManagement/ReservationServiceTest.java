package com.example.restaurantManagement;
import com.example.restaurantManagement.model.*;
import com.example.restaurantManagement.repository.ReservationRepository;
import com.example.restaurantManagement.repository.TablesRepository;
import com.example.restaurantManagement.serviceLayer.CustomerService;
import com.example.restaurantManagement.serviceLayer.ReservationServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private TablesRepository tablesRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private ReservationServiceImplementation reservationService;

    private Reservation testReservation;
    private Customer testCustomer;
    private Tables testTable;

    @BeforeEach
    public void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setName("John Doe");

        testTable = new Tables("T1", 4);

        testReservation = new Reservation();
        testReservation.setId(1);
        testReservation.setCustomerId(1);
        testReservation.setReservationTime("2025-02-05 18:00");
        testReservation.setStatus(ReservationStatus.OCCUPIED);
    }

    @Test
    public void testSaveReservation_Success() {
        when(customerService.getCustomer(testReservation.getCustomerId())).thenReturn(testCustomer);
        when(tablesRepository.findAll()).thenReturn(List.of(testTable));
        when(reservationRepository.save(testReservation)).thenReturn(testReservation);
        Reservation savedReservation = reservationService.saveReservation(testReservation);
        assertNotNull(savedReservation);
        assertEquals(testCustomer, savedReservation.getCustomer());
        assertEquals(testTable, savedReservation.getTable());
        assertEquals(ReservationStatus.OCCUPIED, savedReservation.getStatus());
        verify(reservationRepository).save(testReservation);
    }

    @Test
    public void testSaveReservation_Failed_NoCustomer() {
        when(customerService.getCustomer(testReservation.getCustomerId())).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reservationService.saveReservation(testReservation);
        });

        assertEquals("Customer not found for ID: 1", exception.getMessage());
        verify(reservationRepository, never()).save(any());
    }

    @Test
    public void testFreeTables_Success() {
        Reservation existingReservation = new Reservation();
        existingReservation.setId(1);
        existingReservation.setTable(testTable);
        existingReservation.setStatus(ReservationStatus.OCCUPIED);
        when(reservationRepository.findById(1)).thenReturn(Optional.of(existingReservation));
        when(reservationRepository.save(existingReservation)).thenReturn(existingReservation);
        Reservation freedReservation = reservationService.freeTables(1);
        assertEquals(ReservationStatus.FREED, freedReservation.getStatus());
        assertNull(freedReservation.getTable());
        verify(reservationRepository).save(freedReservation);
    }

    @Test
    public void testFreeTables_Failed_AlreadyFreed() {
        Reservation existingReservation = new Reservation();
        existingReservation.setId(1);
        existingReservation.setTable(testTable);
        existingReservation.setStatus(ReservationStatus.FREED);
        when(reservationRepository.findById(1)).thenReturn(Optional.of(existingReservation));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reservationService.freeTables(1);
        });
        assertEquals("Reservation with ID 1 is already freed.", exception.getMessage());
    }

    @Test
    public void testFreeTables_Failed_ReservationNotFound() {
        when(reservationRepository.findById(1)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reservationService.freeTables(1);
        });
        assertEquals("Reservation not found with ID: 1", exception.getMessage());
    }

    @Test
    public void testRequestTable_Success() {

        when(tablesRepository.findAll()).thenReturn(List.of(testTable));
        when(reservationRepository.findOccupiedReservationsAtTime(anyString(), eq(ReservationStatus.OCCUPIED)))
                .thenReturn(List.of());
        Tables availableTable = reservationService.requestTable(testReservation.getReservationTime());
        assertNotNull(availableTable);
        assertEquals(testTable, availableTable);
    }

//    @Test
//    public void testRequestTable_Failed_NoAvailableTables() {
//        when(tablesRepository.findAll()).thenReturn(List.of(testTable));
//        when(reservationRepository.findOccupiedReservationsAtTime(anyString(), eq(ReservationStatus.OCCUPIED)))
//                .thenReturn(List.of(new Reservation()));
//
//        Tables availableTable = reservationService.requestTable(testReservation.getReservationTime());
//        assertNull(availableTable);
//    }
}
