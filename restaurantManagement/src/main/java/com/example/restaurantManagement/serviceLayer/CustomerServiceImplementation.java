package com.example.restaurantManagement.serviceLayer;

import com.example.restaurantManagement.model.Customer;
import com.example.restaurantManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer(){
        List<Customer> list=customerRepository.findAll();
        if(list.size()==0){
            System.out.println("NO CUSTOMER EXISTS");
            return new ArrayList<>();
        }
        return list;
    }
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public Customer removeCustomer(int id){
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isPresent()){
            Customer res=customer.get();
            customerRepository.deleteById(id);
            System.out.println("Customer : "+ customer.get().getName() +" has been removed");
            return res;
        }
        System.out.println("No customer found");
        return null;
    }

    @Override
    public Customer getCustomer(int id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }
        System.out.println("NO CUSTOMER FOUND WITH ID="+id);
        return null;
    }

    public Customer updateCustomer(int id,Customer customer){
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(existingCustomer.isPresent()){
            existingCustomer.get().setAddress(customer.getAddress());
            existingCustomer.get().setEmail(customer.getEmail());
            existingCustomer.get().setName(customer.getName());
            existingCustomer.get().setPhone(customer.getPhone());
            customerRepository.save(existingCustomer.get());
            return existingCustomer.get();
        }
        System.out.println("NO CUSTOMER FOUND TO UPDATE");
        return null;
    }
}
