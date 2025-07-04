package com.example.bill_generation_system.services;

import com.example.bill_generation_system.entity.Customer;
import com.example.bill_generation_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }
}
