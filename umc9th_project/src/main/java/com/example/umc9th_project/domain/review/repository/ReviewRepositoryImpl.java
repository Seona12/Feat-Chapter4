package com.example.umc9th_project.domain.review.repository;

import com.example.umc9th_project.domain.review.entity.QReview;
import com.example.umc9th_project.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.umc9th_project.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findReviews(Long storeId, Integer rating) {
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(
                        storeEq(storeId),
                        ratingRangeEq(rating)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    // 가게 필터
    private BooleanExpression storeEq(Long storeId) {
        return storeId != null ? review.store.storeId.eq(storeId) : null;
    }

    // 별점 필터 (예: 5점, 4점대, 3점대 ...)
    private BooleanExpression ratingRangeEq(Integer rating) {
        if (rating == null) return null;

        if (rating == 5) return review.star.eq(5F);
        else if (rating == 4) return review.star.between(4.0, 4.9);
        else if (rating == 3) return review.star.between(3.0, 3.9);
        else return review.star.lt(3.0);
    }
}

