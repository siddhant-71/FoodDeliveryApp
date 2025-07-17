package com.siddhant.foodDelivery.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private String image;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @ManyToMany(mappedBy = "dishes")
    @JsonIgnore
    private List<Order> orders=new ArrayList<>();
}
