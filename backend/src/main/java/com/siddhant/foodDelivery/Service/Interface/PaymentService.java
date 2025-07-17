package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.DTOs.PaymentResponse;
import com.siddhant.foodDelivery.Entities.Payment;
import com.siddhant.foodDelivery.Enums.PaymentStatus;

public interface PaymentService {
    PaymentResponse initiatePayment(long orderId);
    Payment completePayment(long paymentId);
    boolean isPaymentCompleted(long paymentId);
    void updatePaymentStatus(long paymentId, PaymentStatus status);
    PaymentStatus getPaymentStatus(long paymentId);
}
