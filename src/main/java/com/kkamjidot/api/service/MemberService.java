package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 이름과 비밀번호로 회원 조회
    public Member login(String name, String password) {
        return memberRepository.findByMemberNameAndMemberPassword(name, password)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
    }

    public Member findOne(String code) throws IllegalStateException {
        return memberRepository.findByMemberPassword(code).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
    }
    
    public void authorization(String code) throws IllegalStateException {
       if (!memberRepository.existsByMemberPassword(code)) throw new IllegalStateException("존재하지 않는 회원입니다.");
    }
}
