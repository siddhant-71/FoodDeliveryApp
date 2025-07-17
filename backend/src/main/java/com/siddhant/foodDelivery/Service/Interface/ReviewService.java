package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.Entities.Review;

public interface ReviewService {
    Review addReview(Review review);
    void updateReview(long ReviewId, Review review);
    void deleteReview(long ReviewId);

    void approveReview(long reviewId);
    void rejectReview(long reviewId);

}