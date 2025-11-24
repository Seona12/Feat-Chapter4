package com.example.umc9th_project.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class MissionRes {

    private Long missionId;
    private String conditional;     // 미션 조건
    private Integer point;          // 성공 점수
    private LocalDate deadline;     // 마감 기한
    private String status;          // 상태
    private String location;        // 위치
    private LocalDateTime createdAt;
}
