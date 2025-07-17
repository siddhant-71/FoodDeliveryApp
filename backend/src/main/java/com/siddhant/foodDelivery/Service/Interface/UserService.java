package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.DTOs.UserDTO;
import com.siddhant.foodDelivery.Entities.User;
import com.siddhant.foodDelivery.Enums.UserRole;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    void updateUser(long userId, User newUser);
    void deleteUser(User user);
    User getUserById(long Id);
    User getUserByEmail(String email);
    User getUserByPhone(String phone);
    List<User> getUsersByRole(UserRole role);

    User AuthenticateUserByEmail(String email,String password);
    User AuthenticateUserByPhone(String phone,String password);
    void changePassword(String email,String oldPassword,String newPassword);
    void resetPassword(String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    long countUsers();

    UserDTO EntityToDTO(User user);
}


