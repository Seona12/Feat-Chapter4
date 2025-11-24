package com.example.umc9th_project.domain.review.service;

import com.example.umc9th_project.domain.review.dto.ReviewReq;
import com.example.umc9th_project.domain.review.dto.ReviewRes;
import com.example.umc9th_project.domain.review.entity.Review;
import com.example.umc9th_project.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewService {
    Page<ReviewRes> getMyReviews(Long memberId, int page);
    List<ReviewRes> getReviews(Long storeId, Integer rating);
    Long createReview(ReviewReq request);
}


