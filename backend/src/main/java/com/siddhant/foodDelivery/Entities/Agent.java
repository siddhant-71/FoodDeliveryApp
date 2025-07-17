package com.siddhant.foodDelivery.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "agents")
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private boolean availability;
    private boolean blocked;
    private String image;
    private double rating ;
    private int ratingCount;
    @OneToMany(mappedBy = "agent")
    @JsonIgnore
    private List<Order> ordersHistory=new ArrayList<>();
}
