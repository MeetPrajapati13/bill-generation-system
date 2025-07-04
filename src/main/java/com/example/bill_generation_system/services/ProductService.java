package com.example.bill_generation_system.services;

import com.example.bill_generation_system.dto.response.ProductResponseDTO;
import com.example.bill_generation_system.entity.Product;
import com.example.bill_generation_system.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductResponseDTO saveProduct(Product product){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        try {
            productRepository.save(product);
            productResponseDTO.setProductName(product.getProductName());
            productResponseDTO.setMessage("Product Detail Saved Successfully");
            productResponseDTO.setHttpStatus(HttpStatus.OK);
            return productResponseDTO;
        }
        catch (Exception e){
            productResponseDTO.setProductName(product.getProductName());
            productResponseDTO.setMessage("Product with this name is already exists");
            productResponseDTO.setHttpStatus(HttpStatus.CONFLICT);
            return productResponseDTO;
        }
    }

    public List<ProductResponseDTO> saveAllProduct(List<Product> products){
        List<ProductResponseDTO> productList = new ArrayList<>();

        for(Product product : products){
            try {
                productRepository.save(product);
                productList.add(new ProductResponseDTO(
                        product.getProductName(),
                        "Added SuccessFully",
                        HttpStatus.OK
                ));
            }catch (Exception e){
                productList.add(new ProductResponseDTO(
                        product.getProductName(),
                        "Product Is Already Added",
                        HttpStatus.CONFLICT
                ));
            }
        }
        return productList;
    }
}
