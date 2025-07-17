package com.siddhant.foodDelivery.Controller;

import com.siddhant.foodDelivery.DTOs.OrderDTO;
import com.siddhant.foodDelivery.Entities.Dish;
import com.siddhant.foodDelivery.Entities.Order;
import com.siddhant.foodDelivery.Entities.User;
import com.siddhant.foodDelivery.Enums.OrderStatus;
import com.siddhant.foodDelivery.Enums.PaymentMethod;
import com.siddhant.foodDelivery.Repository.OrderRepo;
import com.siddhant.foodDelivery.Repository.UserRepo;
import com.siddhant.foodDelivery.Service.Interface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    OrderRepo orderRepo;
    @GetMapping("/")
    List<OrderDTO>getAllOrders(){
        List<Order>list=orderRepo.findAll();
        List<OrderDTO>ans=new ArrayList<>();
        for(Order o:list) {
            ans.add(orderService.EntityToDTO(o));
        }
        return ans;
    }
    @PostMapping("/{userId}")
    public Order placeOrder(@RequestBody List<Dish> dishesList,@PathVariable("userId") long userId,@RequestParam double total){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("user not Found"));
        Order order=new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setDeliveryTime(null);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.INITIATED);
        order.setDishes(dishesList);
        order.setTotalAmount(total);
        user.getOrderHistory().add(order);
        orderRepo.save(order);
        return order;
    }
    @GetMapping("/agent/{id}")
    List<OrderDTO> getOrdersOfAgentWithId(@PathVariable("id") long id){
        List<Order> list=orderRepo.findAllByAgent_Id(id);
        List<OrderDTO>ans=new ArrayList<>();
        for(Order o:list) {
            ans.add(orderService.EntityToDTO(o));
        }
        return ans;
    }
    @GetMapping("/user/{userId}")
    List<OrderDTO> getOrdersOfUser(@PathVariable("userId") long userId){
        List<Order> list=orderRepo.findByUser_Id(userId);
        List<OrderDTO>ans=new ArrayList<>();
        for(Order o:list) {
            ans.add(orderService.EntityToDTO(o));
        }
        return ans;
    }
    @GetMapping("/status/{status}")
    List<OrderDTO> getOrdersByStatus(@PathVariable("status")OrderStatus orderStatus){
        List<Order> list=orderRepo.findAllByOrderStatus(orderStatus);
        List<OrderDTO>ans=new ArrayList<>();
        for(Order o:list) {
            ans.add(orderService.EntityToDTO(o));
        }
        return ans;
    }
}
