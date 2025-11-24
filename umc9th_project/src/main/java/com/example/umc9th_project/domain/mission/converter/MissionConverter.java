package com.example.umc9th_project.domain.mission.converter;

import com.example.umc9th_project.domain.mission.dto.MissionRes;
import com.example.umc9th_project.domain.mission.entity.Mission;

public class MissionConverter {


    public static MissionRes toMissionRes(Mission mission) {
        return MissionRes.builder()
                .missionId(mission.getMissionId())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .status(mission.getStatus())
                .location(mission.getLocation())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
