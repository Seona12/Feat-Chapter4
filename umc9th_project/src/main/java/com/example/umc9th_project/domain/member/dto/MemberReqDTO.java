package com.example.umc9th_project.domain.member.dto;

import com.example.umc9th_project.domain.member.entity.Address;
import com.example.umc9th_project.domain.member.entity.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String specAddress,
            List<Long> preferCategory
    ){}
}
