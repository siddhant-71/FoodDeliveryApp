package com.siddhant.foodDelivery.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String area;
    private String city;
    private String state;
    private double rating;
    private int ratingCount;
    private Integer pincode;
    private String description;
    private String image;
    @OneToOne(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Menu menu;
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> ordersHistory=new ArrayList<>();
}
