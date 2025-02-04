package com.example.restaurantManagement;

import com.example.restaurantManagement.model.Tables;
import com.example.restaurantManagement.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RestaurantManagementApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementApplication.class, args);
	}
	@Autowired
	public TablesRepository tablesRepository;
	@Override
	public void run(String... args) {
		List<String> tableNumbers = List.of("T1", "T2", "T3", "T4", "T5");
		for (String number : tableNumbers) {
			if (tablesRepository.findByTableNumber(number) == null) {
				Tables table = new Tables(number,4);
				tablesRepository.save(table);
			}
		}
	}
}
