package com.siddhant.foodDelivery.DTOs;

import com.siddhant.foodDelivery.Entities.Menu;
import lombok.Data;

@Data
public class RestaurantDTO {
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
    private Menu menu;
}
