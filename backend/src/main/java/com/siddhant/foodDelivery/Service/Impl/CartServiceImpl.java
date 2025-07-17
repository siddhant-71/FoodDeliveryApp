package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.Entities.Cart;
import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.User;
import com.siddhant.foodDelivery.Repository.DishRepo;
import com.siddhant.foodDelivery.Repository.UserRepo;
import com.siddhant.foodDelivery.Service.Interface.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    final static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    @Autowired
    UserRepo userRepo;
    @Autowired
    DishRepo dishRepo;

    @Override
    public Cart createCartForUser(long userId) {
        Cart cart = new Cart();
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        user.setCart(cart);
        return cart;
    }

    @Override
    public Cart getCartByUserId(long userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        if(user.getCart()==null)return new Cart();
        return user.getCart();
    }

    @Override
    public Cart addDishToCart(long userId, long dishId, int quantity) {
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Dish dish=dishRepo.findById(dishId).orElseThrow(()->new RuntimeException("dish not found"));
        for(int i=0;i<quantity;i++)user.getCart().getCartDishes().add(dish);
        userRepo.save(user);
        logger.info("Dish added to cart successfully");
        return user.getCart();
    }

    @Override
    public Cart removeDishFromCart(long userID, long dishId) {
        User user=userRepo.findById(userID).orElseThrow(()->new RuntimeException("user not found"));
        Dish dish=dishRepo.findById(dishId).orElseThrow(()->new RuntimeException("dish not found"));
        user.getCart().getCartDishes().remove(dish);
        userRepo.save(user);
        logger.info("Dish removed from cart successfully");
        return user.getCart();
    }

    @Override
    public double calculateTotalCartValueByUser(long userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        double ans=0;
        for(Dish dish:user.getCart().getCartDishes())ans+=dish.getPrice();
        return ans;
    }
    public double calculateCartTotal(Cart cart){
        double ans=0;
        for(Dish dish:cart.getCartDishes())ans+=dish.getPrice();
        return ans;
    }
}
