package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.DTOs.AddressDTO;
import com.siddhant.foodDelivery.Entities.Address;
import com.siddhant.foodDelivery.Entities.User;
import com.siddhant.foodDelivery.Repository.AddressRepo;
import com.siddhant.foodDelivery.Repository.UserRepo;
import com.siddhant.foodDelivery.Service.Interface.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

   private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public Address addAddress(Address address) {
        try {
            addressRepo.save(address);
            logger.info("Address added successfully");
        }
        catch (Exception e){
            logger.error("Address not added");
        }
        return address;
    }

    @Override
    public Address updateAddressForUser(long userId, Address address) {
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        if(user.getAddress()==null)user.setAddress(address);
        if(address.getHouseNo()!=null)user.getAddress().setHouseNo(address.getHouseNo());
        if(address.getStreet()!=null)user.getAddress().setStreet(address.getStreet());
        if(address.getArea()!=null)user.getAddress().setArea(address.getArea());
        if(address.getCity()!=null)user.getAddress().setCity(address.getCity());
        if(address.getState()!=null)user.getAddress().setState(address.getState());
        if(address.getLandmark()!=null)user.getAddress().setLandmark(address.getLandmark());
        if(address.getPincode()!=0)user.getAddress().setPincode(address.getPincode());
        logger.info("Address for User updated successfully");
        userRepo.save(user);
        addressRepo.save(user.getAddress());
        return user.getAddress();
    }

    @Override
    public void updateAddress(long oldAddressId, Address address) {
       Address oldAddress=addressRepo.findById(oldAddressId).orElseThrow(()->new RuntimeException("Address not found"));
       if(oldAddress.getHouseNo()!=null)oldAddress.setHouseNo(address.getHouseNo());
       if(oldAddress.getStreet()!=null)oldAddress.setStreet(address.getStreet());
       if(oldAddress.getArea()!=null)oldAddress.setArea(address.getArea());
       if(oldAddress.getCity()!=null)oldAddress.setCity(address.getCity());
       if(oldAddress.getState()!=null)oldAddress.setState(address.getState());
       if(oldAddress.getLandmark()!=null)oldAddress.setLandmark(address.getLandmark());
       if(oldAddress.getPincode()!=0)oldAddress.setPincode(address.getPincode());
       logger.info("New Address updated successfully");
       addressRepo.save(oldAddress);
    }

    @Override
    public void deleteAddress(long AddressId) {
        Address address=addressRepo.findById(AddressId).orElseThrow(()->new RuntimeException("Address not found"));
        if(address.getUser()!=null){
            address.getUser().setAddress(null);
            logger.info("Address deleted successfully from user table");
            userRepo.save(address.getUser());
        }
        addressRepo.deleteById(AddressId);
        logger.info("Address deleted successfully");
    }
    public AddressDTO EntityToDTO(Address address){
        AddressDTO ans=new AddressDTO();
        ans.setHouseNo(address.getHouseNo());
        ans.setArea(address.getArea());
        ans.setStreet(address.getStreet());
        ans.setCity(address.getCity());
        ans.setState(address.getState());
        ans.setPincode(address.getPincode());
        ans.setLandmark(address.getLandmark());
        return ans;
    }
}
