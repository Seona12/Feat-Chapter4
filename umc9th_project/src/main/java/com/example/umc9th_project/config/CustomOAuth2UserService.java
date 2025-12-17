package com.example.umc9th_project.config;

import com.example.umc9th_project.auth.Role;
import com.example.umc9th_project.domain.member.entity.Member;
import com.example.umc9th_project.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User user = super.loadUser(userRequest);

        String email = (String) user.getAttributes().get("email");
        String name = (String) user.getAttributes().getOrDefault("name", "social-user");

        memberRepository.findByEmail(email).orElseGet(() -> {
            // 신규 회원 생성(필요 필드 맞춰서 수정)
            Member m = Member.builder()
                    .email(email)
                    .name(name)
                    .password("SOCIAL_LOGIN") // 실제로는 사용 안 함
                    .role(Role.ROLE_USER)
                    .build();
            return memberRepository.save(m);
        });

        return user;
    }
}
