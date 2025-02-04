package com.example.restaurantManagement.repository;

import com.example.restaurantManagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(int orderId);
}
