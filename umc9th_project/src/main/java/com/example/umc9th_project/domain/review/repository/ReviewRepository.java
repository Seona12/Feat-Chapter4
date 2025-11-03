package com.example.umc9th_project.domain.review.repository;

import com.example.umc9th_project.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewRepositoryCustom {
    //1. 리뷰 작성하는 쿼리, * 사진의 경우는 일단 배제 (메서드 생성 방식 권장)
    List<Review> findByStore_StoreIdOrderByCreatedAtDesc(Long storeId);


}
