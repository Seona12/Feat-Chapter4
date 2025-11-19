package com.example.umc9th_project.domain.member.controller;

import com.example.umc9th_project.common.dto.ApiResponseDto;
import com.example.umc9th_project.common.exception.SuccessStatus;
import com.example.umc9th_project.domain.member.dto.MemberReqDTO;
import com.example.umc9th_project.domain.member.dto.MemberResDTO;
import com.example.umc9th_project.domain.member.service.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponseDto<MemberResDTO.JoinDTO> signUp(
            @RequestBody MemberReqDTO.JoinDTO dto
    ){
        return ApiResponseDto.onSuccess(memberCommandService.signup(dto));

    }
}
