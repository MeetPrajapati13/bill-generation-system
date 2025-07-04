package com.example.bill_generation_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "gst_amount")
    private double gstAmount;

    @Column(name = "bill_date")
    private LocalDate billDate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
