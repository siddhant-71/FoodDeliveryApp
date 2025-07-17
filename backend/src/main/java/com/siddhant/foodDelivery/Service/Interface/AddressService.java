package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.DTOs.AddressDTO;
import com.siddhant.foodDelivery.Entities.Address;

public interface AddressService {
    Address addAddress(Address address);
    Address updateAddressForUser(long userId,Address address);
    void updateAddress(long oldAddressId, Address address);
    void deleteAddress(long AddressId);

    AddressDTO EntityToDTO(Address address);
}
