package com.example.umc9th_project.domain.review.controller;

import com.example.umc9th_project.common.dto.ApiResponseDto;

import com.example.umc9th_project.domain.review.dto.ReviewRes;
import com.example.umc9th_project.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponseDto<List<ReviewRes>> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer rating
    ) {
        List<ReviewRes> response = reviewService.getReviews(storeId, rating);

        if (response == null || response.isEmpty()) {
            throw new RuntimeException("조회된 리뷰가 없습니다.");
        }

        return ApiResponseDto.onSuccess(response);
    }
}

