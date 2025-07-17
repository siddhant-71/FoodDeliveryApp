package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.User;
import com.siddhant.foodDelivery.Enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    List<User> findAllByRole(UserRole role);
    List<User> findAllByRoleAndBlocked(String role,boolean blocked);
    List<User> findAllByBlocked(boolean blocked);
    List<User> findAllByBirthDate(LocalDate birthDate);
}
