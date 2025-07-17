package com.siddhant.foodDelivery.DTOs;

import lombok.Data;

@Data
public class AddressDTO {
    private String houseNo;
    private String area;
    private String street;
    private String city;
    private String state;
    private int pincode;
    private String landmark;
}
