package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.Menu;
import com.siddhant.foodDelivery.Entities.Restaurant;
import com.siddhant.foodDelivery.Repository.MenuRepo;
import com.siddhant.foodDelivery.Repository.RestaurantRepo;
import com.siddhant.foodDelivery.Service.Interface.DishService;
import com.siddhant.foodDelivery.Service.Interface.MenuService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuRepo menuRepo;
    @Autowired
    DishService dishService;
    @Autowired
    RestaurantRepo restaurantRepo;
    @Override
    public Menu updateMenu(long menuId, Menu menu) {
        Menu oldMenu=menuRepo.findById(menuId).orElseThrow(()->new RuntimeException("Menu not found"));
        if(menu.getDishes()!=null)oldMenu.setDishes(menu.getDishes());
        if(menu.getRestaurant()!=null)oldMenu.setRestaurant(menu.getRestaurant());
        logger.info("Menu updated successfully");
        menuRepo.save(oldMenu);
        return oldMenu;
    }

    @Override
    public Menu updateMenuForRestaurant(long restaurantId, Menu menu) {
        Restaurant restaurant=restaurantRepo.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
        restaurant.setMenu(menu);
        menu.setRestaurant(restaurant);
        restaurantRepo.save(restaurant);
        menuRepo.save(menu);
        logger.info("Menu updated successfully for Restaurant");
        return menu;
    }

    @Override
    public Dish addDishToMenu(long menuId, Dish dish) {
        Menu menu=menuRepo.findById(menuId).orElseThrow(()->new RuntimeException("Menu not found"));
        if(menu.getDishes()==null)menu.setDishes(new ArrayList<>());
        menu.getDishes().add(dish);
        menuRepo.save(menu);
        dish.setMenu(menu);
        logger.info("Dish added to Menu successfully");
        return dish;
    }

    @Override
    public void deleteDishFromMenu(long menuId, Dish dish) {
        Menu menu=menuRepo.findById(menuId).orElseThrow(()->new RuntimeException("Menu not found"));
        if(menu.getDishes()==null)return;
        for(Dish d:menu.getDishes())if(d.getId()==dish.getId()){
            menu.getDishes().remove(d);
            break;
        }
        menuRepo.save(menu);
        logger.info("Dish deleted from Menu successfully");
        dish.setMenu(null);
    }

    @Override
    public Dish updateDishFromMenu(long menuId, Dish updatedDish, long dishId) {
        Menu menu=menuRepo.findById(menuId).orElseThrow(()->new RuntimeException("Menu not found"));
        Dish dish=menu.getDishes().stream().filter(d->d.getId()==dishId).findFirst().orElseThrow(()->new RuntimeException("Dish not found"));
        menu.getDishes().remove(dish);
        dish=dishService.updateDish(dishId,updatedDish);
        menu.getDishes().add(dish);
        return dish;
    }
}
