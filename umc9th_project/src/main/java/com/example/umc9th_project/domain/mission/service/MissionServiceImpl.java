package com.example.umc9th_project.domain.mission.service;

import com.example.umc9th_project.domain.member.entity.Member;
import com.example.umc9th_project.domain.member.repository.MemberRepository;
import com.example.umc9th_project.domain.mission.entity.MemberMission;
import com.example.umc9th_project.domain.mission.entity.Mission;
import com.example.umc9th_project.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th_project.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Long challengeMission(Long missionId, Long memberId) {

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 이미 도전 중인지 체크
        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new RuntimeException("이미 이 미션을 도전 중입니다.");
        }

        MemberMission memberMission = MemberMission.builder()
                .mission(mission)
                .member(member)
                .isComplete(false)
                .build();

        memberMissionRepository.save(memberMission);

        return memberMission.getMemberMissionId();
    }
}
