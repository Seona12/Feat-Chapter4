package com.example.umc9th_project.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewReq {
    private Long memberId;   // 리뷰 작성자 (NOT NULL)
    private Long storeId;    // 어느 가게 리뷰인지
    private Float star;      // 별점 (float)
    private String content;  // 리뷰 내용
}

