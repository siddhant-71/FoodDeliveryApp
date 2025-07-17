package com.siddhant.foodDelivery.Entities;


import com.siddhant.foodDelivery.Enums.OrderStatus;
import com.siddhant.foodDelivery.Enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private double totalAmount;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Review review;
    @ManyToMany
    @JoinTable(name = "order_dishes",joinColumns = @JoinColumn(name = "order_id"),inverseJoinColumns =@JoinColumn (name = "dish_id"))
    private List<Dish> dishes=new ArrayList<>();

}
