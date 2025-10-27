package com.example.umc9th_project.domain.member.repository;

import com.example.umc9th_project.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 2. 마이 페이지 화면 쿼리 (메서드 생성 방식 권장)
    List<Member> findByMemberId(Long memberId);

}