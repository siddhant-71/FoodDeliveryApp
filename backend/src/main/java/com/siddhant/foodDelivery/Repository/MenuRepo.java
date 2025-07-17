package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepo extends JpaRepository<Menu,Long> {
    Optional<Menu> findByRestaurantId(Long restaurantId);
}
