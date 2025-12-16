package com.example.umc9th_project.domain.member.service;

import com.example.umc9th_project.auth.CustomUserDetails;
import com.example.umc9th_project.common.exception.MemberErrorCode;
import com.example.umc9th_project.common.exception.MemberException;
import com.example.umc9th_project.config.JwtUtil;
import com.example.umc9th_project.domain.member.converter.MemberConverter;
import com.example.umc9th_project.domain.member.dto.MemberReqDTO;
import com.example.umc9th_project.domain.member.dto.MemberResDTO;
import com.example.umc9th_project.domain.member.entity.Member;
import com.example.umc9th_project.domain.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.umc9th_project.common.exception.*;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl{

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public MemberResDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto
    ) {

        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.NOT_FOUND);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }

}
