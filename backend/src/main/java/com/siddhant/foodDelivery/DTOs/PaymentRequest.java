package com.siddhant.foodDelivery.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequest {
    private Double amount;
    private long userid;
    private long orderid;
}
