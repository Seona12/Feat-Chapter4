package com.example.umc9th_project.config;

import com.example.umc9th_project.domain.member.entity.Address;
import com.example.umc9th_project.domain.member.entity.Member;
import com.example.umc9th_project.domain.member.repository.MemberRepository;
import com.example.umc9th_project.domain.review.entity.Review;
import com.example.umc9th_project.domain.review.repository.ReviewRepository;
import com.example.umc9th_project.domain.store.entity.Store;
import com.example.umc9th_project.domain.store.repository.StoreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @PostConstruct
    public void init() {
        System.out.println("=== DataInit start ===");

        Member m1 = memberRepository.save(Member.builder()
                .name("선아")
                .email("test@test.com")
                .address(Address.valueOf("강남구"))
                .build());
        System.out.println("Saved member: " + m1.getId());

        Store s1 = storeRepository.save(Store.builder()
                .name("반이학생마라탕마라반")
                .detailAddress("서울시")
                .build());
        System.out.println("Saved store: " + s1.getStoreId());

        reviewRepository.save(Review.builder()
                .content("맛있어요!")
                .star(5.0f)
                .store(s1)
                .member(m1)
                .build());

        System.out.println("=== DataInit end ===");

        Member m2 = memberRepository.save(Member.builder()
                .name("선아1")
                .email("test@test.com")
                .address(Address.valueOf("강남구"))
                .build());
        System.out.println("Saved member: " + m1.getId());

        Store s2 = storeRepository.save(Store.builder()
                .name("반이학생마라탕마라반")
                .detailAddress("서울시")
                .build());
        System.out.println("Saved store: " + s2.getStoreId());

        reviewRepository.save(Review.builder()
                .content("맛있어요!")
                .star(5.0f)
                .store(s2)
                .member(m2)
                .build());
    }

}
