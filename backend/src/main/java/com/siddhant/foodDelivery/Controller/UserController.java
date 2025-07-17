package com.siddhant.foodDelivery.Controller;


import com.siddhant.foodDelivery.DTOs.LoginBody;
import com.siddhant.foodDelivery.DTOs.UserDTO;
import com.siddhant.foodDelivery.Entities.User;
import com.siddhant.foodDelivery.Enums.UserRole;
import com.siddhant.foodDelivery.Repository.UserRepo;
import com.siddhant.foodDelivery.Service.Interface.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    static final Logger logger= LoggerFactory.getLogger(UserController.class);
    @PostMapping("/login")
    public UserDTO AuthUser(@RequestBody LoginBody loginBody){
        String input=loginBody.getInput();
        String password=loginBody.getPassword();
        boolean isEmail=input.contains("@");
        if(isEmail){
            User user=userRepo.findByEmail(input).orElseThrow(()->new RuntimeException("User not found"));
            if(user.getPassword().equals(password)){
                return userService.EntityToDTO(user);
            }else{
                throw new RuntimeException("Invalid password");
            }
        }
        User user=userRepo.findByPhone(input).orElseThrow(()->new RuntimeException("User not found"));
        if(user.getPassword().equals(password)){
            return userService.EntityToDTO(user);
        }
        throw new RuntimeException("Invalid password");
    }
    @GetMapping("/stateOfUser/{id}")
    public String getStateOfUser(@PathVariable("id")long id){
        User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("user not found"));
        if(user.getAddress()==null)return "";
        return user.getAddress().getState();
    }
    @GetMapping("/cityOfUser/{userId}")
    public String getCityOfUser(@PathVariable("userId")long userId){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        if(user.getAddress()==null)return "";
        return user.getAddress().getCity();
    }
    @GetMapping("/")
    List<UserDTO> getAllUsers(){
        List<User>all=userRepo.findAll();
        List<UserDTO>ans=new ArrayList<>();
        for(User user:all)ans.add(userService.EntityToDTO(user));
        return ans;
    }
    @GetMapping("/id/{UserId}")
    UserDTO getUserWithId(@PathVariable("UserId") long UserId){
        User user=userRepo.findById(UserId).orElseThrow(()->new RuntimeException("User not found"));
        return userService.EntityToDTO(user);
    }
    @GetMapping("/email/{userEmail}")
    UserDTO getUserWithEmail(@PathVariable("userEmail") String userEmail){
        User user=userRepo.findByEmail(userEmail).orElseThrow(()->new RuntimeException("User not found"));
        return userService.EntityToDTO(user);
    }
    @GetMapping("/phone/{userPhone}")
    UserDTO getUserWithPhone(@PathVariable("userPhone") String userPhone){
        User user=userRepo.findByPhone(userPhone).orElseThrow(()->new RuntimeException("User not found"));
        return userService.EntityToDTO(user);
    }
    @GetMapping("/role/{role}")
    List<UserDTO> getUsersWithRole(@RequestParam("role") UserRole role){
        List<User>all=userRepo.findAllByRole(role);
        List<UserDTO>ans=new ArrayList<>();
        for(User user:all)ans.add(userService.EntityToDTO(user));
        return ans;
    }
    @DeleteMapping("/id/{userId}")
    void deleteUserWithId(@PathVariable("userId") long userId){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        userService.deleteUser(user);
        logger.info("User deleted successfully using id - {} ", userId);
    }
    @DeleteMapping("/email/{userEmail}")
    void deleteUserWithEmail(@PathVariable("userEmail") String userEmail){
        User user=userRepo.findByEmail(userEmail).orElseThrow(()->new RuntimeException("User not found"));
        userService.deleteUser(user);
        logger.info("User deleted successfully using email address - {} ", userEmail);
    }
    @DeleteMapping("/phone/{phone}")
    void deleteUserUsingPhone(@RequestParam("phone")String phone){
        User user=userRepo.findByPhone(phone).orElseThrow(()->new RuntimeException("User not found"));
        userService.deleteUser(user);
        logger.info("User deleted successfully using phone number");
    }

    @PostMapping("/addUser")
    void addUser(@RequestBody User user){
        userService.registerUser(user);
        logger.info("User added successfully");
    }
    @PutMapping("/{userId}")
    void updateUser(@RequestBody User user,@PathVariable("userId") long userId){
        userService.updateUser(userId,user);
    }
}