package com.example.umc9th_project.domain.member.service;

import com.example.umc9th_project.domain.member.dto.MemberReqDTO;
import com.example.umc9th_project.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    // 회원가입
    MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );
}
