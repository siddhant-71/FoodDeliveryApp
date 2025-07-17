package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.Menu;

public interface MenuService {
    Menu updateMenu(long menuId, Menu menu);

    Menu updateMenuForRestaurant(long restaurantId, Menu menu);
    Dish addDishToMenu(long menuId, Dish dish);
    void deleteDishFromMenu(long menuId, Dish dish);
    Dish updateDishFromMenu(long menuId, Dish updatedDish,long dishId);
}
