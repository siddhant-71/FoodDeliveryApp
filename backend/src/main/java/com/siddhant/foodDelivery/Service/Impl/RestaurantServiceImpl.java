package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.DTOs.RestaurantDTO;
import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.Menu;
import com.siddhant.foodDelivery.Entities.Restaurant;
import com.siddhant.foodDelivery.Repository.RestaurantRepo;
import com.siddhant.foodDelivery.Service.Interface.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepo restaurantRepo;

    @Override
    public Restaurant registerRestaurant(Restaurant restaurant) {
        restaurantRepo.save(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant updateRestaurant(long Id, Restaurant restaurant) {
        Restaurant oldRestaurant=restaurantRepo.findById(Id).orElseThrow(()->new RuntimeException("Restaurant not found"));
        if(restaurant.getName()!=null)oldRestaurant.setName(restaurant.getName());
        if(restaurant.getArea()!=null)oldRestaurant.setArea(restaurant.getArea());
        if(restaurant.getDescription()!=null)oldRestaurant.setDescription(restaurant.getDescription());
        if(restaurant.getImage()!=null)oldRestaurant.setImage(restaurant.getImage());
        if(restaurant.getCity()!=null)oldRestaurant.setCity(restaurant.getCity());
        if(restaurant.getState()!=null)oldRestaurant.setState(restaurant.getState());
        if(restaurant.getPincode()!=null)oldRestaurant.setPincode(restaurant.getPincode());
        restaurantRepo.save(oldRestaurant);
        return oldRestaurant;
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantRepo.delete(restaurant);
    }

    @Override
    public void updateRestaurantRating(long restaurantId, double rating) {
        Restaurant restaurant=restaurantRepo.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
        double oldRating=restaurant.getRating();
        int count=restaurant.getRatingCount();
        restaurant.setRating((oldRating*count+rating)/(count+1));
        restaurant.setRatingCount(count+1);
        restaurantRepo.save(restaurant);
    }

    @Override
    public List<Dish> getDishesFromRestaurant(long restaurantId) {
        Restaurant restaurant=restaurantRepo.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
        Menu menu=restaurant.getMenu();
        if(menu==null)return null;
        return menu.getDishes();
    }
    public RestaurantDTO EntityToDTO(Restaurant restaurant){
        RestaurantDTO restaurantDTO=new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setArea(restaurant.getArea());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setImage(restaurant.getImage());
        restaurantDTO.setCity(restaurant.getCity());
        restaurantDTO.setState(restaurant.getState());
        restaurantDTO.setPincode(restaurant.getPincode());
        restaurantDTO.setRating(restaurant.getRating());
        restaurantDTO.setRatingCount(restaurant.getRatingCount());
        restaurantDTO.setMenu(restaurant.getMenu());
        return restaurantDTO;
    }
}
