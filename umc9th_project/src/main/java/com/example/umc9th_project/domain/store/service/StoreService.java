package com.example.umc9th_project.domain.store.service;

import com.example.umc9th_project.domain.store.dto.StoreRes;
import com.example.umc9th_project.domain.store.entity.Store;
import com.example.umc9th_project.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Page<StoreRes> searchStores(String region, String keyword, String sort, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> stores = storeRepository.searchStores(region, keyword, sort, pageable);
        return stores.map(StoreRes::from);
    }
}

