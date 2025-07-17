package com.siddhant.foodDelivery.Controller;

import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.Restaurant;
import com.siddhant.foodDelivery.Repository.DishRepo;
import com.siddhant.foodDelivery.Repository.RestaurantRepo;
import com.siddhant.foodDelivery.Service.Interface.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dish")
@CrossOrigin(origins = "http://localhost:5137")
public class DishController {
    @Autowired
    DishRepo dishRepo;
    @Autowired
    DishService dishService;
    @Autowired
    RestaurantRepo restaurantRepo;
    @GetMapping("/{id}")
    public List<Dish> getListofDishFromRestaurant(@RequestParam("id") long id){
        Restaurant restaurant=restaurantRepo.findById(id).orElseThrow(()->new RuntimeException("Restaurant not found"));
        return restaurant.getMenu().getDishes();
    }
    @GetMapping("/name/{name}")
    public Dish getDishWithName(@PathVariable("name") String name){
        return dishRepo.findByName(name).orElseThrow(()->new RuntimeException("Dish not found"));
    }
    @PostMapping("/")
    void addDish(@RequestBody Dish dish){
        dishRepo.save(dish);
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id")long id){
        dishRepo.deleteById(id);
    }
    @PutMapping("/{id}")
    void update(@PathVariable("id")long id, @RequestBody Dish dish){
        dishService.updateDish(id,dish);
    }
}
