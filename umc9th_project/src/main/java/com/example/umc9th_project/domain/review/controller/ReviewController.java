package com.example.umc9th_project.domain.review.controller;

import com.example.umc9th_project.common.dto.ApiResponseDto;

import com.example.umc9th_project.common.validator.ValidPage;
import com.example.umc9th_project.domain.member.entity.Member;
import com.example.umc9th_project.domain.member.repository.MemberRepository;
import com.example.umc9th_project.domain.review.dto.ReviewReq;
import com.example.umc9th_project.domain.review.dto.ReviewRes;
import com.example.umc9th_project.domain.review.entity.Review;
import com.example.umc9th_project.domain.review.repository.ReviewRepository;
import com.example.umc9th_project.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    // 가게의 리뷰 목록 조회
    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 마크 (개발 중)",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })

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

    @PostMapping
    public ApiResponseDto<Long> createReview(@RequestBody ReviewReq request) {
        return ApiResponseDto.onSuccess(reviewService.createReview(request));
    }

    @Operation(summary = "내가 작성한 리뷰 목록 조회",
            description = "member_id로 내가 작성한 리뷰 목록을 page 기준으로 조회합니다. 한 페이지에 10개씩 반환합니다.")
    @GetMapping("/me")
    public ApiResponseDto<Page<ReviewRes>> getMyReviews(
            @Parameter(description = "회원 ID", required = true)
            @RequestParam("member_id") Long memberId,

            @Parameter(description = "페이지 번호 (1 이상)", required = true, example = "1")
            @ValidPage
            @RequestParam("page") Integer page
    ) {
        Page<ReviewRes> result = reviewService.getMyReviews(memberId, page);
        return ApiResponseDto.onSuccess(result);
    }
}
