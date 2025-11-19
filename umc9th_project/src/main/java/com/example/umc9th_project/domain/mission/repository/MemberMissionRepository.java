package com.example.umc9th_project.domain.mission.repository;

import com.example.umc9th_project.domain.member.entity.Member;
import com.example.umc9th_project.domain.mission.entity.MemberMission;
import com.example.umc9th_project.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    //3. 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
    @Query("SELECT m FROM MemberMission m WHERE m.member.id = :memberId AND m.isComplete = :isComplete")
    Page<MemberMission> findByMemberAndIsComplete(
            @Param("memberId") Long memberId,
            @Param("isComplete") Boolean isComplete,
            Pageable pageable
    );
    boolean existsByMemberAndMission(Member member, Mission mission);
}
