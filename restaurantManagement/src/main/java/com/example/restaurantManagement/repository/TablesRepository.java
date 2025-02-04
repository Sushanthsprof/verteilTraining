package com.example.restaurantManagement.repository;

import com.example.restaurantManagement.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablesRepository extends JpaRepository<Tables, Integer> {
    public Tables findByTableNumber(String number);
}