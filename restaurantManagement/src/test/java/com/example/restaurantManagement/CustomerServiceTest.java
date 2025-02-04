package com.example.restaurantManagement;

import com.example.restaurantManagement.model.Customer;
import com.example.restaurantManagement.repository.CustomerRepository;
import com.example.restaurantManagement.serviceLayer.CustomerServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImplementation customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("Nikesh", "padamughal", "9374829374", "nikesh@gmail.com");
        customer.setId(1);
    }

    @Test
    void testSaveCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer res = customerService.saveCustomer(customer);
        assertNotNull(res);
        assertEquals("Nikesh", res.getName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> retrievedCustomers = customerService.getAllCustomer();

        assertFalse(retrievedCustomers.isEmpty());
        assertEquals(1, retrievedCustomers.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer retrievedCustomer = customerService.getCustomer(1);

        assertNotNull(retrievedCustomer);
        assertEquals(1, retrievedCustomer.getId());
        assertEquals("Nikesh", retrievedCustomer.getName());
        verify(customerRepository, times(1)).findById(1);
    }

    @Test
    void testGetCustomerById_NotFound() {
        when(customerRepository.findById(2)).thenReturn(Optional.empty());

        Customer retrievedCustomer = customerService.getCustomer(2);

        assertNull(retrievedCustomer);
        verify(customerRepository, times(1)).findById(2);
    }

    @Test
    void testUpdateCustomer() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer updatedCustomer = new Customer("Nikesh", "Edachira", "9876543210", "nikesh@gmail.com");
        Customer result = customerService.updateCustomer(1, updatedCustomer);

        assertNotNull(result);
        assertEquals("Nikesh", result.getName());
        assertEquals("Edachira", result.getAddress());
        verify(customerRepository, times(1)).findById(1);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testUpdateCustomer_NotFound() {
        when(customerRepository.findById(2)).thenReturn(Optional.empty());

        Customer updatedCustomer = new Customer("Jane Doe", "456 Elm St", "9876543210", "janedoe@example.com");
        Customer result = customerService.updateCustomer(2, updatedCustomer);

        assertNull(result);
        verify(customerRepository, times(1)).findById(2);
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void testDeleteCustomer() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        Customer deletedCustomer = customerService.removeCustomer(1);
        assertNotNull(deletedCustomer);
        assertEquals(1, deletedCustomer.getId());
        verify(customerRepository, times(1)).findById(1);
        verify(customerRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteCustomer_NotFound() {
        when(customerRepository.findById(2)).thenReturn(Optional.empty());
        Customer deletedCustomer = customerService.removeCustomer(2);
        assertNull(deletedCustomer);
        verify(customerRepository, times(1)).findById(2);
        verify(customerRepository, never()).deleteById(anyInt());
    }
}
