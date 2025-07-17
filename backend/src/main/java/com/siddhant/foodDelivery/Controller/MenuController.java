package com.siddhant.foodDelivery.Controller;

import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.Menu;
import com.siddhant.foodDelivery.Entities.Restaurant;
import com.siddhant.foodDelivery.Repository.DishRepo;
import com.siddhant.foodDelivery.Repository.MenuRepo;
import com.siddhant.foodDelivery.Repository.RestaurantRepo;
import com.siddhant.foodDelivery.Service.Impl.DishServiceImpl;
import com.siddhant.foodDelivery.Service.Interface.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "http://localhost:5173")
public class MenuController {

    @Autowired
    RestaurantRepo restaurantRepo;
    @Autowired
    MenuService menuService;
    @Autowired
    MenuRepo menuRepo;
    @Autowired
    DishRepo dishRepo;
    @GetMapping("/{id}")
    Menu getByRestaurantId(@PathVariable("id")long id){
        Restaurant restaurant=restaurantRepo.findById(id).orElseThrow(()->new RuntimeException("Restaurant not found"));
        return restaurant.getMenu();
    }
    @GetMapping("/MenuId/{id}")
    List<Dish> getByMenuId(@PathVariable("id")long id){
        return dishRepo.findAllByMenuId(id);
    }
    @GetMapping("/restaurantId/{id}")
    List<Dish> getDishesByRestaurantId(@PathVariable("id")long id){
        Restaurant restaurant=restaurantRepo.findById(id).orElseThrow(()->new RuntimeException("Restaurant not found"));
        if(restaurant.getMenu()==null)return new ArrayList<>();
        return restaurant.getMenu().getDishes();
    }
    @PostMapping("/addDish/{id}")
    void addDishToRestaurant(@RequestBody Dish dish,@RequestParam("id")long id){
        Menu menu=getByRestaurantId(id);
        menu.getDishes().add(dish);
        menuRepo.save(menu);
        dish.setMenu(menu);
    }
    @DeleteMapping("/{id}")
    void Delete(@PathVariable("id")long id){
        menuRepo.deleteById(id);
    }
    @PutMapping("/{id}")
    void update(@PathVariable("id")long id,@RequestBody Menu menu) {
        menuService.updateMenu(id,menu);
    }
    @PostMapping("/")
    void add(@RequestBody Menu menu){
        menuRepo.save(menu);
    }
}
