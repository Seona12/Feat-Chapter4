package com.example.umc9th_project.domain.review.service;

import com.example.umc9th_project.domain.member.entity.Member;
import com.example.umc9th_project.domain.member.repository.MemberRepository;
import com.example.umc9th_project.domain.review.converter.ReviewConverter;
import com.example.umc9th_project.domain.review.dto.ReviewReq;
import com.example.umc9th_project.domain.review.dto.ReviewRes;
import com.example.umc9th_project.domain.review.entity.Review;
import com.example.umc9th_project.domain.review.repository.ReviewRepository;
import com.example.umc9th_project.domain.store.entity.Store;
import com.example.umc9th_project.domain.store.repository.StoreRepository;
import com.example.umc9th_project.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<ReviewRes> getReviews(Long storeId, Integer rating) {
        List<Review> reviews = reviewRepository.findReviews(storeId, rating);
        return reviews.stream()
                .map(ReviewRes::from)
                .toList();
    }


    @Override
    public Long createReview(ReviewReq request) {

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Review review = Review.builder()
                .store(store)
                .member(member)
                .star(request.getStar())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())  // ⭐ created_at 자동 세팅
                .build();

        reviewRepository.save(review);

        return review.getReviewId();
    }

    public Page<ReviewRes> getMyReviews(Long memberId, int page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Review> reviewPage = reviewRepository.findByMemberId(member.getId(), pageable);

        // Page.map 은 내부적으로 stream 을 사용하므로 for문 사용 X 조건 만족
        return reviewPage.map(ReviewConverter::toReviewRes);
    }

}
