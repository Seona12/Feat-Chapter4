package com.example.umc9th_project.domain.member.entity;

import com.example.umc9th_project.auth.Role;
import com.example.umc9th_project.domain.model.entity.BaseTimeEntity;
import com.example.umc9th_project.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = true)
    private Long id;

    @Column(name = "name", length = 3, nullable = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "gender", nullable = true)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = true)
    private LocalDate birth;

    @Column(name = "address", nullable = true)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address", nullable = true)
    private String detailAddress;

    @Column(name = "social_uid", nullable = true)
    private String socialUid;

    @Column(name = "social_type", nullable = true)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "point", nullable = true)
    private Integer point;


    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews;


}