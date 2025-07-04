package com.example.bill_generation_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotBlank(message = "Name Cannot Be Empty.")
    @Column(name = "product_name", unique = true)
    private String productName;

    @Column(name = "product_price")
    @Min(value = 100,message = "Product Price Must Be Greater Than 100.")
    private double productPrice;

    @Min(value = 1, message = "Product Stock Must Be Grater Than 1.")
    @Column(name = "product_stock")
    private double productStock;

    @Column(name = "gst_rate")
    @Min(value = 1, message = "GST must be at least 1%.")
    private double gstRate;

    @Column(name = "threshold_quantity")
    private int thresholdQuantity;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Order> orders;
}
