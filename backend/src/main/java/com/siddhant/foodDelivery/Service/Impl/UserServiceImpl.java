package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.DTOs.UserDTO;
import com.siddhant.foodDelivery.Entities.User;
import com.siddhant.foodDelivery.Enums.UserRole;
import com.siddhant.foodDelivery.Repository.UserRepo;
import com.siddhant.foodDelivery.Service.Interface.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void registerUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void updateUser(long userId, User newUser) {
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        if(newUser.getName()!=null)user.setName(newUser.getName());
        if(newUser.getLastName()!=null)user.setLastName(newUser.getLastName());
        if(newUser.getBirthDate()!=null)user.setBirthDate(newUser.getBirthDate());
        if(newUser.getEmail()!=null)user.setEmail(newUser.getEmail());
        if(newUser.getPhone()!=null)user.setPhone(newUser.getPhone());
        if(newUser.getRole()!=null)user.setRole(newUser.getRole());
        if(newUser.getAddress()!=null)user.setAddress(newUser.getAddress());
        userRepo.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepo.delete(user);
        logger.info("User deleted successfully");
    }

    @Override
    public User getUserById(long Id) {
        return userRepo.findById(Id).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public User getUserByPhone(String phone) {
        return userRepo.findByPhone(phone).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public List<User> getUsersByRole(UserRole role) {
        return userRepo.findAllByRole(role);
    }

    @Override
    public User AuthenticateUserByEmail(String email, String password) {
        User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        if(user.getPassword().equals(password))return user;
        else throw new RuntimeException("Invalid Credentials");
    }

    @Override
    public User AuthenticateUserByPhone(String phone, String password) {
        User user=userRepo.findByPhone(phone).orElseThrow(()->new RuntimeException("User not found"));
        if(user.getPassword().equals(password))return user;
        else throw new RuntimeException("Invalid Credentials");
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {
        User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("No user with this Email - "+ email));
        if(user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
            userRepo.save(user);
            logger.info("Password changed successfully");
        }
        logger.error("Password not changed, incorrect old password");
    }

    @Override
    public void resetPassword(String email) {
        User user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("No user with this Email - "+ email));
        logger.info("Password reset link has sent to your email - "+ email);
    }

    @Override
    public boolean existsByEmail(String email) {
        User user=userRepo.findByEmail(email).orElse(null);
        if(user==null)return false;
        else return true;
    }

    @Override
    public boolean existsByPhone(String phone) {
        User user=userRepo.findByPhone(phone).orElse(null);
        if(user==null)return false;
        else return true;
    }

    @Override
    public long countUsers() {
        return userRepo.count();
    }

    public UserDTO EntityToDTO(User user){
        UserDTO ans=new UserDTO();
        ans.setAddress(user.getAddress());
        ans.setId(user.getId());
        ans.setCart(user.getCart());
        ans.setBirthDate(user.getBirthDate());
        ans.setEmail(user.getEmail());
        ans.setPhone(user.getPhone());
        ans.setName(user.getName());
        ans.setLastName(user.getLastName());
        ans.setRole(user.getRole());
        ans.setOrderHistory(user.getOrderHistory());
        return ans;
    }
}
