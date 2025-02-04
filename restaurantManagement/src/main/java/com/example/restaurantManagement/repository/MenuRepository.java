package com.example.restaurantManagement.repository;

import com.example.restaurantManagement.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
}
