package com.example.umc9th_project.domain.mission.dto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class MyMissionRes {

    private Long missionId;
    private String conditional;
    private Integer point;
    private String status;
    private String location;
    private LocalDate deadline;
    private LocalDateTime createdAt;
    private String storeName;
}

