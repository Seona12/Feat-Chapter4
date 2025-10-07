package com.example.umc9th_project.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodType name;  // KOREAN, JAPANESE, CHINESE, etc.

    // 1:N - 한 음식이 여러 사용자 선호와 연결
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFood> memberFoods = new ArrayList<>();

    public void addMemberFood(MemberFood memberFood) {
        memberFoods.add(memberFood);
        memberFood.setFood(this);
    }
}
