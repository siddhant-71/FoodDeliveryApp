package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepo extends JpaRepository<Dish,Long> {
    Optional<Dish> findByName(String name);
    List<Dish> findAllByMenuId(Long menuId);
}
