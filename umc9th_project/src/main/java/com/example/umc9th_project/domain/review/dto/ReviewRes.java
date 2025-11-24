package com.example.umc9th_project.domain.review.dto;


import com.example.umc9th_project.domain.review.entity.Review;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ReviewRes(
        Long id,
        String content,
        Float star,
        String storeName,
        Long storeId,
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
                .storeId(review.getStore().getStoreId())
                .build();
    }

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}
}