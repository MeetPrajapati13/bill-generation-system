package com.example.bill_generation_system.controller;

import com.example.bill_generation_system.entity.Customer;
import com.example.bill_generation_system.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("add-customer")
    public ResponseEntity<?> addCustomer(@RequestBody @Valid Customer customer){
       customerService.saveCustomer(customer);

       return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","Customer created successfully"));
    }
}
