package com.siddhant.foodDelivery.DTOs;

import com.siddhant.foodDelivery.Entities.Order;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AgentDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String image;
    private double rating ;
    private int ratingCount;
    private List<Order> ordersHistory=new ArrayList<>();
}
