package com.example.umc9th_project.domain.store.entity;

import com.example.umc9th_project.domain.location.entity.Location;
import com.example.umc9th_project.domain.model.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = true)
    private String name; // 가게명

    @Column(nullable = true)
    private Long managerNumber; // 사장님 구분 번호

    @Column(nullable = true)
    private String detailAddress; // 상세 주소

    // 지역과의 연관관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

}
