package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.DTOs.OrderDTO;
import com.siddhant.foodDelivery.DTOs.OrderRequest;
import com.siddhant.foodDelivery.Entities.Agent;
import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.Order;
import com.siddhant.foodDelivery.Enums.OrderStatus;
import com.siddhant.foodDelivery.Repository.AgentRepo;
import com.siddhant.foodDelivery.Repository.OrderRepo;
import com.siddhant.foodDelivery.Repository.RestaurantRepo;
import com.siddhant.foodDelivery.Repository.UserRepo;
import com.siddhant.foodDelivery.Service.Interface.CartService;
import com.siddhant.foodDelivery.Service.Interface.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
@Service
public class OrderServiceImpl implements OrderService {

    static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    AgentRepo agentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    RestaurantRepo restaurantRepo;
    @Autowired
    CartService cartService;
    @Override
    public Order placeOrder(long userId, OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderStatus(OrderStatus.INITIATED);
        order.setOrderTime(LocalDateTime.now());
        order.setPayment(null);
        order.setDishes(orderRequest.getDishes());
        order.setUser(userRepo.findById(orderRequest.getUserId()).get());
        order.setRestaurant(restaurantRepo.findById(orderRequest.getRestaurantId()).get());
        order.setTotalAmount(orderRequest.getAmount());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        return order;
    }
    @Override
    public void updateOrderStatus(long OrderId, OrderStatus status) {
        Order order=orderRepo.findById(OrderId).orElseThrow(()->new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        orderRepo.save(order);
    }

    @Override
    public String getOrderStatus(long OrderId) {
        Order order=orderRepo.findById(OrderId).orElseThrow(()->new RuntimeException("Order not found"));
        return order.getOrderStatus().toString();
    }

    @Override
    public void assignDeliveryAgent(long orderId, long AgentId) {
        Order order=orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("order not found"));
        Agent agent=agentRepo.findById(AgentId).orElseThrow(()->new RuntimeException("Agent not found"));
        if(!agent.isAvailability())logger.error("Agent is Not Available ");
        agent.setAvailability(false);
        agent.getOrdersHistory().add(order);
        agentRepo.save(agent);
        order.setAgent(agent);
        order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
        orderRepo.save(order);
        logger.info("Agent assigned to order successfully");
    }

    @Override
    public void unassignDeliveryAgent(long OrderId, long AgentId) {
        Order order=orderRepo.findById(OrderId).orElseThrow(()->new RuntimeException("Order not found"));
        Agent agent=agentRepo.findById(AgentId).orElseThrow(()->new RuntimeException("Agent not found"));
        if(order.getAgent()!=null){
            if(order.getAgent().getId()==AgentId){
                agent.getOrdersHistory().remove(order);
                order.setAgent(null);
                agentRepo.save(agent);
                orderRepo.save(order);
                logger.info("Agent unassigned from order successfully");
            }
        }
    }

    @Override
    public double calculateOrderTotal(long orderId) {
        Order order=orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        double ans=0;
        for(Dish dish:order.getDishes())ans+=dish.getPrice();
        return ans;
    }

    public OrderDTO EntityToDTO(Order order){
        OrderDTO ans=new OrderDTO();
        ans.setId(order.getId());
        if(order.getOrderTime()!=null)ans.setOrderTime(order.getOrderTime());
        if(order.getDeliveryTime()!=null)ans.setDeliveryTime(order.getDeliveryTime());
        if(order.getOrderStatus()!=null)ans.setOrderStatus(order.getOrderStatus());
        if(order.getPaymentMethod()!=null)ans.setPaymentMethod(order.getPaymentMethod());
        ans.setTotalAmount(order.getTotalAmount());
        if(order.getUser()!=null)ans.setUserName(order.getUser().getName());
        if(order.getRestaurant()!=null)ans.setRestaurantName(order.getRestaurant().getName());
        ans.setAgentName(order.getAgent()==null?"No Agent Assigned":order.getAgent().getName());
        if(order.getDishes()!=null)ans.setDishes(order.getDishes());
        return ans;
    }
}
