package com.example.umc9th_project.domain.review.service;

import com.example.umc9th_project.domain.review.dto.ReviewRes;
import com.example.umc9th_project.domain.review.entity.Review;
import com.example.umc9th_project.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewRes> getReviews(Long storeId, Integer rating) {
        List<Review> reviews = reviewRepository.findReviews(storeId, rating);
        return reviews.stream()
                .map(ReviewRes::from)
                .toList();
    }
}


