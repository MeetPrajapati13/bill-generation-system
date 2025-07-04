package com.example.bill_generation_system.controller;

import com.example.bill_generation_system.dto.request.OrderRequest;
import com.example.bill_generation_system.dto.response.OrderResponseDTO;
import com.example.bill_generation_system.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public OrderResponseDTO addOrder(@RequestBody OrderRequest orderRequest){
        return orderService.validateAndSaveOrder(orderRequest);
    }
}
