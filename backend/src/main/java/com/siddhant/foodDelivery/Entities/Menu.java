package com.siddhant.foodDelivery.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="menus")
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Dish> dishes=new ArrayList<>();
    @OneToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
}
