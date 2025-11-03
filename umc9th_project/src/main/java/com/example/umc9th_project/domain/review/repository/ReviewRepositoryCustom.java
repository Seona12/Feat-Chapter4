package com.example.umc9th_project.domain.review.repository;

import com.example.umc9th_project.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findReviews(Long storeId, Integer rating);
}
