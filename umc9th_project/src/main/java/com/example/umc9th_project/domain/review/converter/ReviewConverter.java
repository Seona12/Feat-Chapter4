package com.example.umc9th_project.domain.review.converter;

import com.example.umc9th_project.domain.review.dto.ReviewRes;
import com.example.umc9th_project.domain.review.entity.Review;

public class ReviewConverter {
    public static ReviewRes toReviewRes(Review review) {
        return ReviewRes.builder()
                .id(review.getReviewId())
                .storeId(review.getStore().getStoreId())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
