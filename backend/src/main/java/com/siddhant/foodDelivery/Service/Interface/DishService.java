package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.Entities.Dish;

public interface DishService {
    Dish addDish(Dish dish);
    Dish updateDish(long DishId,Dish UpdatedDish);
}
