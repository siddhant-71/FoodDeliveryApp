package com.siddhant.foodDelivery.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String houseNo;
    private String area;
    private String street;
    private String city;
    private String state;
    private int pincode;
    private String landmark;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
