package com.example.restaurantManagement;
import com.example.restaurantManagement.dto.OrderItemRequest;
import com.example.restaurantManagement.dto.OrderResponse;
import com.example.restaurantManagement.model.*;
import com.example.restaurantManagement.repository.CustomerRepository;
import com.example.restaurantManagement.repository.MenuRepository;
import com.example.restaurantManagement.repository.OrderItemRepository;
import com.example.restaurantManagement.repository.OrderRepository;
import com.example.restaurantManagement.serviceLayer.OrderServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderServiceImplementation orderService;

    private Customer testCustomer;
    private Menu testMenu;
    private Order testOrder;
    private OrderItem testOrderItem;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer("Nikesh", "Padamughal", "1234567890", "nikesh@gmail.com");
        testCustomer.setId(1);

        testMenu = new Menu();
        testMenu.setId(1);
        testMenu.setName("Noodles");
        testMenu.setPrice(100.0);

        testOrder = new Order();
        testOrder.setId(1);
        testOrder.setCustomer(testCustomer);
        testOrder.setStatus(OrderStatus.PENDING);

        testOrderItem = new OrderItem(testOrder, testMenu, 2);
        testOrderItem.setId(1);
    }

    @Test
    void testCreateOrder_Success() {
        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setMenuId(1);
        orderItemRequest.setQuantity(2);

        when(customerRepository.findById(1)).thenReturn(Optional.of(testCustomer));
        when(menuRepository.findById(1)).thenReturn(Optional.of(testMenu));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setId(1);
            return savedOrder;
        });

        OrderResponse response = orderService.createOrder(1, OrderStatus.PENDING, List.of(orderItemRequest));

        assertNotNull(response);
        assertEquals(1, response.getId());
        assertEquals(1, response.getCustomerId());
        assertEquals(OrderStatus.PENDING, response.getStatus());
        assertEquals(200.0, response.getTotalPrice());

        verify(customerRepository, times(1)).findById(1);
        verify(menuRepository, times(1)).findById(1);
        verify(orderRepository, times(1)).save(any(Order.class));
    }


    @Test
    void testCreateOrder_CustomerNotFound() {
        when(customerRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                orderService.createOrder(99, OrderStatus.PENDING, List.of(new OrderItemRequest())));

        assertEquals("Customer not found", exception.getMessage());
        verify(customerRepository, times(1)).findById(99);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void testCreateOrder_MenuItemNotFound() {
        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setMenuId(99);
        orderItemRequest.setQuantity(2);

        when(customerRepository.findById(1)).thenReturn(Optional.of(testCustomer));
        when(menuRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                orderService.createOrder(1, OrderStatus.PENDING, List.of(orderItemRequest)));

        assertEquals("Menu item not found", exception.getMessage());
        verify(menuRepository, times(1)).findById(99);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void testCancelOrder_Success() {
        when(orderRepository.findById(1)).thenReturn(Optional.of(testOrder));

        orderService.cancelOrder(1);

        assertEquals(OrderStatus.CANCELLED, testOrder.getStatus());
        verify(orderRepository, times(1)).save(testOrder);
    }

    @Test
    void testCancelOrder_AlreadyCompleted() {
        testOrder.setStatus(OrderStatus.COMPLETED);
        when(orderRepository.findById(1)).thenReturn(Optional.of(testOrder));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                orderService.cancelOrder(1));

        assertEquals("The ORDER IS ALREADY CANCELLED", exception.getMessage());
        verify(orderRepository, never()).save(testOrder);
    }

    @Test
    void testCancelOrder_OrderNotFound() {
        when(orderRepository.findById(99)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> orderService.cancelOrder(99));

        verify(orderRepository, times(1)).findById(99);
        verify(orderRepository, never()).save(any(Order.class));
    }
}


