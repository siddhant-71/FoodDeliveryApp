package com.siddhant.foodDelivery.DTOs;

import com.siddhant.foodDelivery.Enums.PaymentStatus;
import lombok.Data;

@Data
public class PaymentResponse {
    private String paymentId;
    private PaymentStatus paymentStatus;
    private String redirectUrl;
}
