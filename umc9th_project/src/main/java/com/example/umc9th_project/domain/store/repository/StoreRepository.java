package com.example.umc9th_project.domain.store.repository;

import com.example.umc9th_project.domain.review.entity.Review;
import com.example.umc9th_project.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long>{

}
