package com.nagarro.training.repository;

import com.nagarro.training.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {


    Review findById(int id);

    List<Review> findReviewByCode(String code);



    @Query(value = "SELECT IFNULL(AVG(r.rating),0) FROM Review r WHERE r.code=?1 AND r.approve_status= true", nativeQuery = true)
    double findAverageRatings(String code);

    @Query(value = "SELECT COUNT(r.rating) FROM Review r WHERE r.code=?1 AND r.approve_status= true", nativeQuery = true)
    double countReviews(String code);

}