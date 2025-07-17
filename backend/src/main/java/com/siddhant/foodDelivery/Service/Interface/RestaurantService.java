package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.DTOs.RestaurantDTO;
import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant registerRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(long Id,Restaurant restaurant);
    void deleteRestaurant(Restaurant restaurant);
    void updateRestaurantRating(long restaurantId, double rating);
    List<Dish> getDishesFromRestaurant(long restaurantId);
    RestaurantDTO EntityToDTO(Restaurant res);
}
