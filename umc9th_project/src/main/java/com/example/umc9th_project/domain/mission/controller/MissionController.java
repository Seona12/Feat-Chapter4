package com.example.umc9th_project.domain.mission.controller;

import com.example.umc9th_project.common.dto.ApiResponseDto;
import com.example.umc9th_project.common.validator.ValidPage;
import com.example.umc9th_project.domain.mission.dto.MissionRes;
import com.example.umc9th_project.domain.mission.dto.MyMissionRes;
import com.example.umc9th_project.domain.mission.service.MissionService;
import com.example.umc9th_project.domain.mission.service.MyMissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final MyMissionService myMissionService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponseDto<Long> challengeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId
    ) {
        Long memberMissionId = missionService.challengeMission(missionId, memberId);
        return ApiResponseDto.onSuccess(memberMissionId);
    }

    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "store_id 기준으로 해당 가게의 미션 목록을 page 기준으로 조회합니다. 한 페이지에 10개씩 반환합니다."
    )
    @GetMapping("/store")
    public ApiResponseDto<Page<MissionRes>> getStoreMissions(
            @Parameter(description = "가게 ID", required = true)
            @RequestParam("store_id") Long storeId,

            @Parameter(description = "페이지 번호 (1 이상)", example = "1", required = true)
            @ValidPage
            @RequestParam("page") Integer page
    ) {

        Page<MissionRes> result = missionService.getMissionsByStore(storeId, page);
        return ApiResponseDto.onSuccess(result);
    }


    @Operation(
            summary = "내가 진행중인 미션 목록 조회",
            description = "member_id 기준으로 완료되지 않은(isComplete=false) 미션을 조회합니다. 페이지당 10개 반환합니다."
    )
    @GetMapping("/me")
    public ApiResponseDto<Page<MyMissionRes>> getMyOngoingMissions(
            @Parameter(description = "회원 ID", required = true)
            @RequestParam("member_id") Long memberId,

            @Parameter(description = "페이지 번호 (1 이상)", example = "1", required = true)
            @ValidPage
            @RequestParam("page") Integer page
    ) {
        Page<MyMissionRes> result = myMissionService.getMyOngoingMissions(memberId, page);
        return ApiResponseDto.onSuccess(result);
    }
}
