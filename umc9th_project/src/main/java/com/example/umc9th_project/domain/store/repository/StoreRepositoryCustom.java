package com.example.umc9th_project.domain.store.repository;

import com.example.umc9th_project.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreRepositoryCustom {
    Page<Store> searchStores(String region, String keyword, String sort, Pageable pageable);
}

