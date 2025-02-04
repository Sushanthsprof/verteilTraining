package com.example.restaurantManagement.serviceLayer;
import com.example.restaurantManagement.dto.OrderItemRequest;
import com.example.restaurantManagement.dto.OrderItemResponse;
import com.example.restaurantManagement.dto.OrderResponse;
import com.example.restaurantManagement.model.*;
import com.example.restaurantManagement.repository.CustomerRepository;
import com.example.restaurantManagement.repository.MenuRepository;
import com.example.restaurantManagement.repository.OrderItemRepository;
import com.example.restaurantManagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderResponse> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            System.out.println("NO ORDERS EXIST");
            return new ArrayList<>();
        }

        return orders.stream().map(order -> {
                    double totalPrice = order.getItems().stream().mapToDouble(item -> item.getMenu().getPrice() * item.getQuantity()).sum();
                    List<OrderItemResponse> itemResponses = order.getItems().stream().map(item -> new OrderItemResponse(item.getMenu().getName(), item.getQuantity(), item.getMenu().getPrice(), item.getMenu().getPrice() * item.getQuantity())).collect(Collectors.toList());
                    return new OrderResponse(order.getId(),order.getCustomer().getId(),order.getCustomer().getName(),order.getCustomer().getPhone(), order.getStatus(), itemResponses, totalPrice);
                }).collect(Collectors.toList());
    }

    @Override
    public OrderResponse createOrder(int customerId, OrderStatus status, List<OrderItemRequest> orderItemsRequest) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(status);
        List<OrderItem> orderItems = orderItemsRequest.stream().map(itemRequest -> {
                    Menu menu = menuRepository.findById(itemRequest.getMenuId()).orElseThrow(() -> new RuntimeException("Menu item not found"));OrderItem orderItem = new OrderItem();orderItem.setMenu(menu);orderItem.setQuantity(itemRequest.getQuantity());orderItem.setOrder(order);return orderItem;
        }).collect(Collectors.toList());
        order.setItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        double totalPrice = savedOrder.getItems().stream()
                .mapToDouble(item -> item.getMenu().getPrice() * item.getQuantity())
                .sum();
        List<OrderItemResponse> itemResponses = savedOrder.getItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getMenu().getName(),
                        item.getQuantity(),
                        item.getMenu().getPrice(),
                        item.getMenu().getPrice() * item.getQuantity()
                ))
                .collect(Collectors.toList());
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getCustomer().getId(),
                savedOrder.getCustomer().getName(),
                savedOrder.getCustomer().getPhone(),
                savedOrder.getStatus(),
                itemResponses,
                totalPrice
        );
    }
    @Override
    public void cancelOrder(int id) {
        Optional<Order> order=orderRepository.findById(id);
        if(order.isPresent() && !order.get().getStatus().equals(OrderStatus.COMPLETED)){
            order.get().setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order.get());
            System.out.println("The order has been cancelled");
        }
        else if(order.isPresent() && order.get().getStatus().equals(OrderStatus.COMPLETED)){
            throw new RuntimeException("The ORDER IS ALREADY CANCELLED");
        }
        else{
            System.out.println("NO SUCH ORDER EXISTS");
        }
    }
}

