package com.example.umc9th_project.domain.mission.controller;

import com.example.umc9th_project.common.dto.ApiResponseDto;
import com.example.umc9th_project.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponseDto<Long> challengeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId
    ) {
        Long memberMissionId = missionService.challengeMission(missionId, memberId);
        return ApiResponseDto.onSuccess(memberMissionId);
    }
}
