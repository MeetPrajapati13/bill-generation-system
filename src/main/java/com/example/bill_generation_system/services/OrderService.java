package com.example.bill_generation_system.services;

import com.example.bill_generation_system.dto.request.OrderRequest;
import com.example.bill_generation_system.dto.response.OrderResponseDTO;
import com.example.bill_generation_system.entity.Customer;
import com.example.bill_generation_system.entity.Order;
import com.example.bill_generation_system.entity.Product;
import com.example.bill_generation_system.repository.CustomerRepository;
import com.example.bill_generation_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BillService billService;

    public OrderResponseDTO validateAndSaveOrder(OrderRequest orderRequest){
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        Optional<Product> productOptional =  productRepository.findById(orderRequest.getProductId());

        if(productOptional.isEmpty()){
            orderResponseDTO.setMessage("Product Not Found");
            orderResponseDTO.setHttpStatus(HttpStatus.NOT_FOUND);
            return orderResponseDTO;
        }

        Optional<Customer> customerOptional = customerRepository.findById(orderRequest.getCustomerId());

        if(customerOptional.isEmpty()){
            orderResponseDTO.setMessage("Customer Not Found");
            orderResponseDTO.setHttpStatus(HttpStatus.NOT_FOUND);
            return orderResponseDTO;
        }

        Product product = productOptional.get();

        if(orderRequest.getQuantity() > product.getProductStock()){
            orderResponseDTO.setMessage("Oops! You’ve selected more than we have in stock");
            orderResponseDTO.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return orderResponseDTO;
        }

        Customer customer = customerOptional.get();

        return billService.generateBill(customer, product, orderRequest.getQuantity());

    }

}
