package com.example.umc9th_project.domain.member.entity;

import com.example.umc9th_project.domain.model.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 이름

    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별

    private LocalDate birth; //생년월일

    @Enumerated(EnumType.STRING)
    private Address address; // 주소

    private String detail_address; // 상세주소

    private String socialuId; //소셜 UID

    @Enumerated(EnumType.STRING)
    private SocialType socialType; //소셜 타입

    private int point; //포인트
    private String email; // 이메일
    private String phoneNumber; //전화번호

    //삭제일자, 수정일자는 BaseTimeEntity

}
