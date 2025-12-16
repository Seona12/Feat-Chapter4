package com.example.umc9th_project.domain.member.repository;

import com.example.umc9th_project.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 2. 마이 페이지 화면 쿼리 (메서드 생성 방식 권장)
    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String username);
}