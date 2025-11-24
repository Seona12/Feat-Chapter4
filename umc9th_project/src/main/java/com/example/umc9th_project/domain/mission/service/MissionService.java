package com.example.umc9th_project.domain.mission.service;

import com.example.umc9th_project.domain.mission.dto.MissionRes;
import org.springframework.data.domain.Page;

public interface MissionService {
    Page<MissionRes> getMissionsByStore(Long storeId, int page);
    Long challengeMission(Long missionId, Long memberId);
}

