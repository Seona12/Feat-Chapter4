package com.example.umc9th_project.domain.term.entity;

package com.example.umc9th_project.domain.term.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TermType name; // AGE, SERVICE, PRIVACY, LOCATION, MARKETING

    // 1:N - 하나의 약관이 여러 사용자 약관과 연결
    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTerm> memberTerms = new ArrayList<>();

}

