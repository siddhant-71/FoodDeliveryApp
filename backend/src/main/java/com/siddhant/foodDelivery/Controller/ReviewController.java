package com.siddhant.foodDelivery.Controller;

import com.siddhant.foodDelivery.Entities.Review;
import com.siddhant.foodDelivery.Repository.ReviewRepo;
import com.siddhant.foodDelivery.Service.Interface.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:5137")
public class ReviewController {

    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    ReviewService reviewService;

    @PostMapping("/addReview")
    public void addReview(@RequestBody Review review){
        reviewRepo.save(review);
    }
    @PutMapping("/update/{id}")
    public void update(@RequestBody Review review , @PathVariable("id")long id){
        reviewService.updateReview(id,review);
    }
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable("id") long id){
        reviewService.deleteReview(id);
    }
}
