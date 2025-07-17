package com.siddhant.foodDelivery.Repository;

import com.siddhant.foodDelivery.Entities.Review;
import com.siddhant.foodDelivery.Enums.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review,Long> {
    Optional<Review> findByOrderId(Long orderId);
    List<Review> findAllByUserId(Long userId);
    List<Review> findAllByReviewStatus(ReviewStatus status);
    List<Review> findAllByRatingBetween(double start,double end);
    List<Review> findAllByRatingGreaterThanEqual(double rating);
    List<Review> findAllByRatingLessThanEqual(double rating);
}
