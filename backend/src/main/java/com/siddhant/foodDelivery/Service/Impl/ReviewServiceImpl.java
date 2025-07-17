package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.Entities.Review;
import com.siddhant.foodDelivery.Enums.ReviewStatus;
import com.siddhant.foodDelivery.Repository.ReviewRepo;
import com.siddhant.foodDelivery.Service.Interface.ReviewService;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepo reviewRepo;
    @Override
    public Review addReview(Review review) {
        reviewRepo.save(review);
        return review;
    }

    @Override
    public void updateReview(long ReviewId, Review review) {
        Review review1=reviewRepo.findById(ReviewId).orElseThrow(()->new RuntimeException("Review not found"));
        if(review.getComment()!=null)review1.setComment(review.getComment());
        review1.setReviewTime(LocalDateTime.now());
    }

    @Override
    public void deleteReview(long ReviewId) {
        Review review=reviewRepo.findById(ReviewId).orElseThrow(()->new RuntimeException("Review not found"));
        reviewRepo.delete(review);
    }

    @Override
    public void approveReview(long reviewId) {
        Review review=reviewRepo.findById(reviewId).orElseThrow(()->new RuntimeException("Review not found"));
        review.setReviewStatus(ReviewStatus.ACCEPTED);
    }

    @Override
    public void rejectReview(long reviewId) {
        Review review=reviewRepo.findById(reviewId).orElseThrow(()->new RuntimeException("Review not found"));
        review.setReviewStatus(ReviewStatus.REJECTED);
    }
}
