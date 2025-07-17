package com.siddhant.foodDelivery.Controller;

import com.siddhant.foodDelivery.Entities.Payment;
import com.siddhant.foodDelivery.Enums.PaymentStatus;
import com.siddhant.foodDelivery.Repository.PaymentRepo;
import com.siddhant.foodDelivery.Service.Interface.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5137")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentRepo paymentRepo;

    @GetMapping("/OrderId/{id}")
    public Payment getByOrderId(@PathVariable("id")long id){
        return paymentRepo.findByOrderId(id).get();
    }
    @GetMapping("/Trans/{id}")
    public Payment getByTransId(@PathVariable("id")String id){
        return paymentRepo.findByTransactionId(id).get();
    }
    @GetMapping("/{status}")
    public List<Payment> getByStatus(@RequestParam("status")PaymentStatus status){
        return paymentRepo.findAllByPaymentStatus(status);
    }
}
