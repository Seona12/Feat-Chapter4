package com.example.umc9th_project.domain.review.dto;


import com.example.umc9th_project.domain.review.entity.Review;
import lombok.Builder;

@Builder
public record ReviewRes(
        Long id,
        String content,
        Float star,
        String storeName,
        String createdAt
) {
    public static ReviewRes from(Review review) {
        return ReviewRes.builder()
                .id(review.getReviewId())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt() != null
                        ? review.getCreatedAt().toString()
                        : "(no date)")
                .build();
    }
}