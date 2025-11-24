package com.example.umc9th_project.domain.mission.repository;

import com.example.umc9th_project.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    //4. 홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)
    //isComplete는 MemberMission의 필드지만,이 쿼리는 “Mission 목록”을 조회하는 용도이므로 MissionRepository에 작성이 맞다
    // MemberMission은 단지 “이미 완료된 미션을 제외하기 위한 조인 조건”으로 사용된 것뿐.
    @Query("""
    SELECT m 
    FROM Mission m 
    LEFT JOIN MemberMission mm 
      ON mm.mission.missionId = m.missionId AND mm.member.id = :memberId AND mm.isComplete = true
    WHERE m.location = :location 
      AND mm.mission.missionId IS NULL
""")
    Page<Mission> findAvailableMissionsByLocationAndMemberJoin(
            @Param("location") String location,
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    Page<Mission> findByStore_StoreId(Long storeId, Pageable pageable);

}
