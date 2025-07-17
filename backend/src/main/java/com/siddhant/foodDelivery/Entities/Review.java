package com.siddhant.foodDelivery.Entities;


import com.siddhant.foodDelivery.Enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private double rating;
    private LocalDateTime reviewTime;
    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
