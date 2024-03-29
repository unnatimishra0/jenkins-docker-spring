package com.nagarro.training.service;

import com.nagarro.training.entity.Review;
import com.nagarro.training.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;


    @Override
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> fetchReviewByCode(String code) {
        List<Review> listReview = reviewRepository.findReviewByCode(code);
        return listReview.stream().filter(Review::isApproveStatus).
                collect(Collectors.toList());
    }

    @Override
    public void disapproveReview(int reviewId) {
        Review review = reviewRepository.findById(reviewId);
        review.setApproveStatus(false);
        this.saveReview(review);

    }

    @Override
    public List<Review> approved() {
        List<Review> listReview = reviewRepository.findAll();


        return listReview.stream().filter(Review::isApproveStatus).
                collect(Collectors.toList());
    }

    @Override
    public List<Review> notApproved() {
        List<Review> listReview = reviewRepository.findAll();


        return listReview.stream().filter(reviews -> !reviews.isApproveStatus()).
                collect(Collectors.toList());
    }

    @Override
    public void approveReview(int id) {
        Review review = reviewRepository.findById(id);
        review.setApproveStatus(true);
        this.saveReview(review);
    }

    @Override
    public double findAverageRating(String code) {
        return reviewRepository.findAverageRatings(code);
    }

    @Override
    public double countReviews(String code) {
        return reviewRepository.countReviews(code);
    }
}


