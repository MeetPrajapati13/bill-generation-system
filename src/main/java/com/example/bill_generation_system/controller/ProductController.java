package com.example.bill_generation_system.controller;

import com.example.bill_generation_system.dto.response.ProductResponseDTO;
import com.example.bill_generation_system.entity.Product;
import com.example.bill_generation_system.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public ProductResponseDTO add(@Valid @RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PostMapping("/add-Multiple-Product")
    public List<ProductResponseDTO> addMultipleProduct(@RequestBody List<Product> products){
        return productService.saveAllProduct(products);
    }

}
