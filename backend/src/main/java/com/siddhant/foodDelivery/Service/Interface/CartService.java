package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.Entities.Cart;

public interface CartService {

    Cart createCartForUser(long UserId);
    Cart getCartByUserId(long UserId);
    Cart addDishToCart(long userId,long DishId,int quantity);
    Cart removeDishFromCart(long userID,long DishId);
    double calculateTotalCartValueByUser(long UserId);

}
