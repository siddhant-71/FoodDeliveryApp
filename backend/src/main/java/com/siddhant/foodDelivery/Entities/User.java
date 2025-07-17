package com.siddhant.foodDelivery.Entities;

import com.siddhant.foodDelivery.Enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String phone;
    private boolean blocked;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Address address;
    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Cart cart;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orderHistory=new ArrayList<>();
}
