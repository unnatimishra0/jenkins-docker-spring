package com.nagarro.training.service;

import com.nagarro.training.entity.Review;

import java.util.List;

public interface ReviewService {

    void saveReview(Review review);

    List<Review> fetchReviewByCode(String code);


    void disapproveReview(int reviewId);

    List<Review> approved();

    List<Review> notApproved();


    void approveReview(int id);

    double findAverageRating(String code);

    double countReviews(String code);
}
