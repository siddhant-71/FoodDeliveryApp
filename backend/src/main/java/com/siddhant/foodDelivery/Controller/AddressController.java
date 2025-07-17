package com.siddhant.foodDelivery.Controller;

import com.siddhant.foodDelivery.DTOs.AddressDTO;
import com.siddhant.foodDelivery.Entities.Address;
import com.siddhant.foodDelivery.Entities.User;
import com.siddhant.foodDelivery.Repository.AddressRepo;
import com.siddhant.foodDelivery.Repository.UserRepo;
import com.siddhant.foodDelivery.Service.Interface.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5137")
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    AddressRepo addressRepo;

    @GetMapping("/{userId}")
    public AddressDTO getAddressOfUser(@RequestParam("userId")long userId){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        return addressService.EntityToDTO(user.getAddress());
    }

    @PostMapping("/{id}")
    void add(@RequestBody Address address, @PathVariable("id")long id){
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        user.setAddress(address);
        userRepo.save(user);
        address.setUser(user);
        addressRepo.save(address);
    }

    @PutMapping("/{id}")
    void update(@RequestBody Address address, @PathVariable("id")long id){

        addressService.updateAddress(id,address);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id")long id){
        addressService.deleteAddress(id);
    }
}
