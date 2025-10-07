package com.example.umc9th_project.domain.mission.entity;

import com.example.umc9th_project.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    @Column(nullable = false)
    private LocalDate deadline; // 미션 기한

    @Column(nullable = false)
    private String conditional; // 미션 조건

    @Column(nullable = false)
    private Integer point; // 성공 점수

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // --- 연관 관계 ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true) // [양방향] 미션 삭제될 때 사용자 미션도 삭제.
    private List<MemberMission> memberMissions = new ArrayList<>();

}

