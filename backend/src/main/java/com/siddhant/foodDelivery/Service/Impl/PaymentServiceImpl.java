package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.DTOs.PaymentResponse;
import com.siddhant.foodDelivery.Entities.Order;
import com.siddhant.foodDelivery.Entities.Payment;
import com.siddhant.foodDelivery.Enums.PaymentStatus;
import com.siddhant.foodDelivery.Repository.OrderRepo;
import com.siddhant.foodDelivery.Repository.PaymentRepo;
import com.siddhant.foodDelivery.Service.Interface.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import org.springframework.stereotype.Service;
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    PaymentRepo PaymentRepo;
    @Override
    public PaymentResponse initiatePayment(long orderId) {
        Order order=orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        String redirectUrl="/razorpay";
        String paymentId= UUID.randomUUID().toString();
        PaymentResponse paymentResponse=new PaymentResponse();
        paymentResponse.setPaymentId(paymentId);
        paymentResponse.setPaymentStatus(PaymentStatus.PENDING);
        paymentResponse.setRedirectUrl(redirectUrl);
        return paymentResponse;
    }

    @Override
    public Payment completePayment(long paymentId) {
        Payment payment=PaymentRepo.findById(paymentId).orElseThrow(()->new RuntimeException("Payment not found"));
        if(payment.getPaymentStatus()==PaymentStatus.PENDING)payment.setPaymentStatus(PaymentStatus.SUCCESS);
        return payment;

    }

    @Override
    public boolean isPaymentCompleted(long paymentId) {
        Payment payment=PaymentRepo.findById(paymentId).orElseThrow(()->new RuntimeException("Payment not found"));
        return payment.getPaymentStatus()==PaymentStatus.SUCCESS;
    }

    @Override
    public void updatePaymentStatus(long paymentId, PaymentStatus status) {
        Payment payment=PaymentRepo.findById(paymentId).orElseThrow(()->new RuntimeException("Payment not found"));
        payment.setPaymentStatus(status);
        PaymentRepo.save(payment);
    }

    @Override
    public PaymentStatus getPaymentStatus(long paymentId) {
        Payment payment=PaymentRepo.findById(paymentId).orElseThrow(()->new RuntimeException("Payment not found"));
        return payment.getPaymentStatus();
    }
}
