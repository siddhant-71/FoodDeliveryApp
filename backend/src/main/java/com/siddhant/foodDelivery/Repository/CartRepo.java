package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository <Cart,Long> {
    Cart findByUserId(Long userId);
}
