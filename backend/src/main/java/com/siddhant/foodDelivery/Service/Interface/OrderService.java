package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.DTOs.OrderDTO;
import com.siddhant.foodDelivery.DTOs.OrderRequest;
import com.siddhant.foodDelivery.Entities.Order;
import com.siddhant.foodDelivery.Enums.OrderStatus;

public interface OrderService {
    Order placeOrder(long userId, OrderRequest orderRequest);
    void updateOrderStatus(long OrderId, OrderStatus status);
    String getOrderStatus(long OrderId);
    void assignDeliveryAgent(long orderId,long AgentId);
    void unassignDeliveryAgent(long OrderId,long  AgentId);
    double calculateOrderTotal(long orderId);

    OrderDTO EntityToDTO(Order o);
}
