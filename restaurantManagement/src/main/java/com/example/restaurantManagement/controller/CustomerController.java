package com.example.restaurantManagement.controller;

import com.example.restaurantManagement.model.Customer;
import com.example.restaurantManagement.serviceLayer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cc")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/saveCustomer")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public Customer deleteCustomer(@PathVariable int id){
        return customerService.removeCustomer(id);
    }

    @GetMapping("/getAllCustomer")
    public List<Customer> getCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/getCustomer/{id}")
    public Customer getCustomer(@PathVariable int id){
        return customerService.getCustomer(id);
    }

    @PutMapping("/updateCustomer/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        return customerService.updateCustomer(id,customer);
    }
}
