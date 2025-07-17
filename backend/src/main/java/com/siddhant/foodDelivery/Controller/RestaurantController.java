package com.siddhant.foodDelivery.Controller;

import com.siddhant.foodDelivery.DTOs.RestaurantDTO;
import com.siddhant.foodDelivery.Entities.Restaurant;
import com.siddhant.foodDelivery.Repository.RestaurantRepo;
import com.siddhant.foodDelivery.Service.Interface.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantController {
    @Autowired
    RestaurantRepo restaurantRepo;
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/all")
    public List<RestaurantDTO> getAllRestaurants(){
        List<Restaurant>list=restaurantRepo.findAll();
        List<RestaurantDTO>ans=new ArrayList<>();
        for(Restaurant res:list)ans.add(restaurantService.EntityToDTO(res));
        return ans;
    }
    @GetMapping("/area/{area}")
    public List<RestaurantDTO> getRestaurantByArea(@PathVariable("area")String area){
        List<RestaurantDTO>ans=new ArrayList<>();
        List<Restaurant>list=restaurantRepo.findAllByArea(area);
        for(Restaurant res:list)ans.add(restaurantService.EntityToDTO(res));
        return ans;
    }
    @GetMapping("/city/{city}")
    public List<RestaurantDTO> getRestaurantByCity(@PathVariable("city")String city){
        List<RestaurantDTO>ans=new ArrayList<>();
        List<Restaurant>list=restaurantRepo.findAllByArea(city);
        for(Restaurant res:list)ans.add(restaurantService.EntityToDTO(res));
        return ans;
    }
    @GetMapping("/state/{state}")
    public List<RestaurantDTO> getRestaurantByState(@PathVariable("state")String state){
        List<RestaurantDTO>ans=new ArrayList<>();
        List<Restaurant>list=restaurantRepo.findAllByArea(state);
        for(Restaurant res:list)ans.add(restaurantService.EntityToDTO(res));
        return ans;
    }
    @GetMapping("/rating/{rating}")
    public List<RestaurantDTO> getRestaurantByArea(@PathVariable("rating")double rating){
        List<RestaurantDTO>ans=new ArrayList<>();
        List<Restaurant>list=restaurantRepo.findAllByRatingGreaterThanEqual(rating);
        for(Restaurant res:list)ans.add(restaurantService.EntityToDTO(res));
        return ans;
    }
    @GetMapping("/pincode/{pincode}")
    public List<RestaurantDTO> getRestaurantByPincode(@PathVariable("pincode")int pincode){
        List<RestaurantDTO>ans=new ArrayList<>();
        List<Restaurant>list=restaurantRepo.findAllByPincode(pincode);
        for(Restaurant res:list)ans.add(restaurantService.EntityToDTO(res));
        return ans;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")long id){
        restaurantRepo.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id")long id,@RequestBody Restaurant restaurant){
        restaurantService.updateRestaurant(id,restaurant);
    }

    @PostMapping("/add")
    public void add(@RequestBody Restaurant restaurant){
        restaurantRepo.save(restaurant);
    }

}
