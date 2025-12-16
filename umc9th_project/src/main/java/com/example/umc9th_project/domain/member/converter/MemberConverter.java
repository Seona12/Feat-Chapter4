package com.example.umc9th_project.domain.member.converter;

import com.example.umc9th_project.auth.Role;
import com.example.umc9th_project.domain.member.dto.MemberReqDTO;
import com.example.umc9th_project.domain.member.dto.MemberResDTO;
import com.example.umc9th_project.domain.member.entity.Member;

public class MemberConverter {

    // Entity -> Join DTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO + Password + Role -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ) {
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .gender(dto.gender())
                .build();
    }

    // Entity + AccessToken -> Login DTO
    public static MemberResDTO.LoginDTO toLoginDTO(
            Member member,
            String accessToken
    ) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}
