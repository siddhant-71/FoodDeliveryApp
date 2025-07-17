package com.siddhant.foodDelivery.Entities;

import com.siddhant.foodDelivery.Enums.PaymentMethod;
import com.siddhant.foodDelivery.Enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String transactionId;
    private double amount;
    private String paymentMessage;

    @OneToOne(mappedBy = "payment")
    @JsonIgnore
    private Order order;

}
