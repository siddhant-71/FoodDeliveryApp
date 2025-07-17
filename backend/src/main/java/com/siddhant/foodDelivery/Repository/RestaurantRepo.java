package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findAllByArea(String area);
    List<Restaurant> findAllByCity(String city);
    List<Restaurant> findAllByRatingGreaterThanEqual(double rating);
    List<Restaurant> findAllByState(String state);
    List<Restaurant> findAllByPincode(int pincode);
    Optional<Restaurant> findByMenuId(Long menuId);
}
