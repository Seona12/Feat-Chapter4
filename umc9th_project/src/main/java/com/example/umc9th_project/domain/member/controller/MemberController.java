package com.example.umc9th_project.domain.member.controller;

import com.example.umc9th_project.common.dto.ApiResponseDto;
import com.example.umc9th_project.common.exception.SuccessStatus;
import com.example.umc9th_project.domain.member.dto.MemberReqDTO;
import com.example.umc9th_project.domain.member.dto.MemberResDTO;
import com.example.umc9th_project.domain.member.service.MemberCommandService;
import com.example.umc9th_project.domain.member.service.MemberQueryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryServiceImpl memberQueryServiceImpl;


    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponseDto<MemberResDTO.JoinDTO> signUp(
            @RequestBody MemberReqDTO.JoinDTO dto
    ){
        return ApiResponseDto.onSuccess(memberCommandService.signup(dto));

    }

    // 로그인
    @PostMapping("/login")
    public ApiResponseDto<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ApiResponseDto.onSuccess(memberQueryServiceImpl.login(dto));
    }


}
