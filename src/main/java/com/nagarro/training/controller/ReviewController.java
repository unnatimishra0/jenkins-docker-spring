package com.nagarro.training.controller;

import com.nagarro.training.entity.Review;
import com.nagarro.training.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    @Autowired
    ReviewService reviewService;


    @GetMapping("/approved")
    public List<Review> approvedReview() {
        return reviewService.approved();

    }

    @GetMapping("/notApproved")
    public List<Review> notApprovedReview() {
        return reviewService.notApproved();

    }

    @GetMapping("/approve/{id}")
    public void updateReview(@PathVariable("id") int reviewId) {

        reviewService.approveReview(reviewId);

    }

    @DeleteMapping("/disapprove/{id}")
    public void deleteReview(@PathVariable("id") int reviewId) {
        reviewService.disapproveReview(reviewId);
    }


    @PostMapping("/reviews")
    public ResponseEntity<String> postReview(@RequestBody Review review) {
        reviewService.saveReview(review);
        return ResponseEntity.ok("Review saved");
    }


    @GetMapping("/reviews/{code}")
    public List<Review> reviewDetails(@PathVariable("code") String code) {
        List<Review> reviewList = null;
        if (code != null) {
            reviewList = reviewService.fetchReviewByCode(code);
        }
        return reviewList;
    }

}
