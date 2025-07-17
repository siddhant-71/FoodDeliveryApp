package com.siddhant.foodDelivery.DTOs;

import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Enums.PaymentMethod;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private long addressId;
    private long restaurantId;
    private long userId;
    private PaymentMethod paymentMethod;
    private double amount;
    private List<Dish> dishes;
}
