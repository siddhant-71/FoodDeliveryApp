package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepo extends JpaRepository<Address,Long> {
    Optional<Address> findByUserId(Long userId);
    List<Address> findAllByArea(String area);
    List<Address> findAllByCity(String city);
    List<Address> findAllByState(String state);
    List<Address> findAllByPincode(int pincode);
}
