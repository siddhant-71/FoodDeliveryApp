package com.siddhant.foodDelivery.DTOs;

import com.siddhant.foodDelivery.Entities.Address;
import com.siddhant.foodDelivery.Entities.Cart;
import com.siddhant.foodDelivery.Entities.Order;
import com.siddhant.foodDelivery.Enums.UserRole;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private UserRole role;
    private Address address;
    private Cart cart;
    private List<Order> orderHistory;
}
