package com.siddhant.foodDelivery.Controller;


import com.siddhant.foodDelivery.Entities.Cart;
import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.User;
import com.siddhant.foodDelivery.Repository.CartRepo;
import com.siddhant.foodDelivery.Repository.DishRepo;
import com.siddhant.foodDelivery.Repository.UserRepo;
import com.siddhant.foodDelivery.Service.Impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    DishRepo dishRepo;
    @Autowired
    CartServiceImpl cartService;
    @GetMapping("/{id}")
    public Cart getCart(@PathVariable("id") long id){
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        if(user.getCart()==null){
            Cart cart=new Cart();
            cartRepo.save(cart);
            user.setCart(cart);
            userRepo.save(user);
            return cart;
        }
        return user.getCart();
    }
    @GetMapping("/{id}/{DishId}")
    List<Dish> addDishToCart(@PathVariable("DishId")long DishId, @PathVariable("id") long id){
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        Dish dish=dishRepo.findById(DishId).orElseThrow(()->new RuntimeException("Dish Not Found"));
        Cart cart=user.getCart();
        if(cart==null){
            cart=new Cart();
            user.setCart(cart);
            cart.setUser(user);
            cartRepo.save(cart);
        }
        cart.getCartDishes().add(dish);
        cartRepo.save(cart);
        return getCart(id).getCartDishes();
    }@GetMapping("/delete/{id}/{DishId}")
    List<Dish> DeleteDishFromCart(@PathVariable("DishId")long DishId, @PathVariable("id") long id){
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        Dish dish=dishRepo.findById(DishId).orElseThrow(()->new RuntimeException("Dish Not Found"));
        Cart cart=user.getCart();
        cart.getCartDishes().remove(dish);
        cartRepo.save(cart);
        return getCart(id).getCartDishes();
    }
    @DeleteMapping("/{id}")
    void ClearCart(@PathVariable("id")long id){
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        user.setCart(null);
        return;
    }
}
