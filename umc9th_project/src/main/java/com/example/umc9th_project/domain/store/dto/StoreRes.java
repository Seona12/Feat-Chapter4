package com.example.umc9th_project.domain.store.dto;

import com.example.umc9th_project.domain.store.entity.Store;
import lombok.Builder;

@Builder
public record StoreRes(
        Long id,
        String name,
        String region,
        String address,
        String createdAt
) {
    public static StoreRes from(Store store) {
        return StoreRes.builder()
                .id(store.getStoreId())
                .name(store.getName())
                .region(store.getDetailAddress())
                .createdAt(store.getCreatedAt() != null
                        ? store.getCreatedAt().toString()
                        : "")
                .build();
    }
}
