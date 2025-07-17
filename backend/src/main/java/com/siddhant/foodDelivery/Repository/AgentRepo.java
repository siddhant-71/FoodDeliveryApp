package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgentRepo extends JpaRepository<Agent,Long> {
    Optional<Agent> findByEmail(String email);
    Optional<Agent> findByPhone(String phone);
    List<Agent> findAllByBlocked(boolean blocked);
    List<Agent> findAllByName(String name);
    List<Agent> findAllByRatingBetween(double start,double end);
    List<Agent> findAllByRatingGreaterThanEqual(double rating);
    List<Agent> findAllByAvailability(boolean availability);
}
