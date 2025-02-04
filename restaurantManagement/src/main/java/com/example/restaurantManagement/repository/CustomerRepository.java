package com.example.restaurantManagement.repository;

import com.example.restaurantManagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
