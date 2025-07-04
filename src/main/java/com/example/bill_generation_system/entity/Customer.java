package com.example.bill_generation_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_phone", columnNames = "phone_number")
        }
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @NotBlank(message = "Invalid Customer Name")
    @Column(name = "customer_name")
    private String customerName;

    @Column(name="phone_number")
    @Pattern(regexp = "^[1-9][0-9]{9}$",message = "Invalid phone number")
    @NotBlank(message = "phoneNumber customer name")
    private String phoneNumber;

    @Column(name="email")
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z A-Z]{2,3}$",message = "Invalid email")
    private String email;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Order> orders;


}
