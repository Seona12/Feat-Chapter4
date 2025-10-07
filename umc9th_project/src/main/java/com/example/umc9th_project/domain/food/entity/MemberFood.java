package com.example.umc9th_project.domain.food.entity;

import com.example.umc9th_project.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberFoodId;

    // --- 연관관계 ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
