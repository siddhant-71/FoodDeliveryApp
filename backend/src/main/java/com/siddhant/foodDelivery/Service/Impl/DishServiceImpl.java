package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Repository.DishRepo;
import com.siddhant.foodDelivery.Service.Interface.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DishServiceImpl implements DishService {

    private static final Logger logger= LoggerFactory.getLogger(DishServiceImpl.class);
    @Autowired
    DishRepo dishRepo;
    @Override
    public Dish addDish(Dish dish) {
        dishRepo.save(dish);
        logger.info("Dish added successfully");
        return dish;
    }

    @Override
    public Dish updateDish(long dishId, Dish updatedDish) {
        Dish dish=dishRepo.findById(dishId).orElseThrow(()->new RuntimeException("Dish Not Found"));
        if(updatedDish.getName()!=null)dish.setName(updatedDish.getName());
        if(updatedDish.getPrice()!=0)dish.setPrice(updatedDish.getPrice());
        if(updatedDish.getDescription()!=null)dish.setDescription(updatedDish.getDescription());
        if(updatedDish.getImage()!=null)dish.setImage(updatedDish.getImage());
        dishRepo.save(dish);
        return dish;
    }

}
