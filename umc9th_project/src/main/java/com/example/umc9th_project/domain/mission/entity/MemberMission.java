package com.example.umc9th_project.domain.mission.entity;

import com.example.umc9th_project.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMissionId;

    @Column(nullable = false)
    private Boolean isComplete = false; // 완료 여부

    // --- 연관 관계 ---
    @ManyToOne(fetch = FetchType.LAZY) // 필수
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY) // 필수
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}
