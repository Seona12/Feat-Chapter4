package com.example.umc9th_project.domain.mission.service;

import com.example.umc9th_project.domain.member.repository.MemberRepository;
import com.example.umc9th_project.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th_project.domain.mission.dto.MyMissionRes;
import com.example.umc9th_project.domain.mission.entity.MemberMission;
import com.example.umc9th_project.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th_project.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public Page<MyMissionRes> getMyOngoingMissions(Long memberId, int page) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<MemberMission> missionPage =
                memberMissionRepository.findByMember_IdAndIsCompleteFalse(memberId, pageable);

        return missionPage.map(MemberMissionConverter::toMyMissionRes);
    }
}

