package com.example.bill_generation_system.dto.response;

import org.springframework.http.HttpStatus;

public class ProductResponseDTO {
    private String productName;

    private String message;

    private HttpStatus httpStatus;

    public ProductResponseDTO(String productName, String message, HttpStatus httpStatus) {
        this.productName = productName;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ProductResponseDTO() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
