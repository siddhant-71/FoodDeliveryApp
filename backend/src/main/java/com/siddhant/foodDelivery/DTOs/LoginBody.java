package com.siddhant.foodDelivery.DTOs;

import lombok.Data;

@Data
public class LoginBody {
    private String input;
    private String password;
    private boolean Email;
}
