package com.example.restaurantManagement.serviceLayer;

import com.example.restaurantManagement.dto.OrderItemRequest;
import com.example.restaurantManagement.dto.OrderRequest;
import com.example.restaurantManagement.dto.OrderResponse;
import com.example.restaurantManagement.model.Customer;
import com.example.restaurantManagement.model.Order;
import com.example.restaurantManagement.model.OrderStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderService {
    public void cancelOrder(int id);
    public OrderResponse createOrder(int customerId, OrderStatus status, List<OrderItemRequest> orderItemsRequest);
    public List<OrderResponse> findAllOrders();
}
