package com.example.bill_generation_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "quantity")
    @Min(value = 1, message = "Order Quantity At Least 1.")
    private double quantity;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "fk_product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;


}
