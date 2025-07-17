package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.Payment;
import com.siddhant.foodDelivery.Enums.PaymentMethod;
import com.siddhant.foodDelivery.Enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
    Optional<Payment> findByOrderId(Long orderId);
    Optional<Payment> findByTransactionId(String transactionId);
    List<Payment> findAllByPaymentStatus(PaymentStatus status);
    List<Payment> findAllByPaymentMethod(PaymentMethod paymentMethod);
    List<Payment> findAllByAmountBetween(double start,double end);
    List<Payment> findAllByAmountGreaterThanEqual(double amount);
    List<Payment> findAllByAmountLessThanEqual(double amount);
}
