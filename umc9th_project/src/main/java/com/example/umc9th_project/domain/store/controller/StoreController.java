package com.example.umc9th_project.domain.store.controller;

import com.example.umc9th_project.domain.store.dto.StoreRes;
import com.example.umc9th_project.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/search")
    public ResponseEntity<Page<StoreRes>> searchStores(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "latest") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<StoreRes> result = storeService.searchStores(region, keyword, sort, page, size);
        return ResponseEntity.ok(result);
    }
}

