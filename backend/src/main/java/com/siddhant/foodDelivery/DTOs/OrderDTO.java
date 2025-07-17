package com.siddhant.foodDelivery.DTOs;

import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Enums.OrderStatus;
import com.siddhant.foodDelivery.Enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private double totalAmount;
    private String userName;
    private String restaurantName;
    private String agentName;
    private List<Dish> dishes=new ArrayList<>();
}
