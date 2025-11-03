package com.example.umc9th_project.domain.store.repository;

import com.example.umc9th_project.domain.store.entity.QStore;
import com.example.umc9th_project.domain.store.entity.Store;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Store> searchStores(String region, String keyword, String sort, Pageable pageable) {
        QStore store = QStore.store;

        // 조건 조립
        BooleanBuilder builder = new BooleanBuilder();

        // 지역 필터
        if (region != null && !region.isBlank()) {
            builder.and(store.detailAddress.eq(region));
        }

        // 이름 검색
        if (keyword != null && !keyword.isBlank()) {
            if (keyword.contains(" ")) {
                // 공백 포함: 각 단어가 포함된 가게의 합집합 (OR 조건)
                String[] words = keyword.split("\\s+");
                BooleanBuilder nameBuilder = new BooleanBuilder();
                for (String word : words) {
                    nameBuilder.or(store.name.containsIgnoreCase(word));
                }
                builder.and(nameBuilder);
            } else {
                // 공백 없음: 전체 포함 검색 (단일 LIKE)
                builder.and(store.name.containsIgnoreCase(keyword));
            }
        }

        // 정렬 조건
        OrderSpecifier<?> orderSpecifier;
        if ("name".equals(sort)) {
            orderSpecifier = store.name.asc().nullsLast();
        } else {
            orderSpecifier = store.createdAt.desc();
        }

        // 전체 쿼리
        List<Store> results = queryFactory
                .selectFrom(store)
                .where(builder)
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 카운트 쿼리
        long total = queryFactory
                .select(store.count())
                .from(store)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }
}

