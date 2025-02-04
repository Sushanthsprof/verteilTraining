package com.example.restaurantManagement.serviceLayer;


import com.example.restaurantManagement.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);
    public Customer removeCustomer(int id);
    public Customer getCustomer(int id);
    public Customer updateCustomer(int id,Customer customer);
    public List<Customer> getAllCustomer();
}
