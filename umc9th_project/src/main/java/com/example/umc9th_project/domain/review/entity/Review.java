package com.example.umc9th_project.domain.review.entity;

import com.example.umc9th_project.domain.member.entity.Member;
import com.example.umc9th_project.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = true)
    private Float star;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    // 외래키 (store_id, user_id)
    private Long userId;

    // 연관관계 매핑 - ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 연관관계 매핑 - OneToMany
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhoto> photos = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    //리뷰 여러개에 store 하나.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = true)
    private Store store;

}
