package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.Order;
import com.siddhant.foodDelivery.Enums.OrderStatus;
import com.siddhant.foodDelivery.Enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByUser_Id(Long userId);
    List<Order> findAllByRestaurant_Id(Long restaurantId);
    List<Order> findAllByAgent_Id(Long agentId);
    List<Order> findAllByOrderStatus(OrderStatus status);
    List<Order> findAllByRestaurant_IdAndOrderStatus(Long restaurantId,OrderStatus status);
    List<Order> findAllByAgent_IdAndOrderStatus(Long agentId,OrderStatus status);
    List<Order> findAllByAgent_IdAndRestaurant_Id(Long agentId,Long restaurantId);
    List<Order> findAllByAgent_IdAndRestaurant_IdAndOrderStatus(Long agentId,Long restaurantId,OrderStatus status);
    List<Order> findAllByPaymentMethod(PaymentMethod paymentMethod);
    List<Order> findAllByPaymentMethodAndOrderStatus(PaymentMethod paymentMethod,String status);
}
