package com.example.umc9th_project.domain.mission.converter;

import com.example.umc9th_project.domain.mission.dto.MyMissionRes;
import com.example.umc9th_project.domain.mission.entity.MemberMission;

public class MemberMissionConverter {

    public static MyMissionRes toMyMissionRes(MemberMission mm) {
        return MyMissionRes.builder()
                .missionId(mm.getMission().getMissionId())
                .conditional(mm.getMission().getConditional())
                .point(mm.getMission().getPoint())
                .status(mm.getMission().getStatus())
                .location(mm.getMission().getLocation())
                .deadline(mm.getMission().getDeadline())
                .createdAt(mm.getMission().getCreatedAt())
                .storeName(mm.getMission().getStore().getName())
                .build();
    }
}