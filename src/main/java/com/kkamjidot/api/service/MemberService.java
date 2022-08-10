package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.exception.UserNotFoundException;
import com.kkamjidot.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    // 이름과 비밀번호로 회원 조회
    public Member login(String name, String password) throws NoSuchElementException{
        return memberRepository.findByMemberNameAndMemberPassword(name, password)
                .orElseThrow(() -> new UserNotFoundException());
    }

    public Member findOne(String code) throws UserNotFoundException {
        return memberRepository.findByMemberPassword(code).orElseThrow(() -> new UserNotFoundException());
    }
    
    public void authorization(String code) throws UserNotFoundException {
       if (!memberRepository.existsByMemberPassword(code)) throw new UserNotFoundException();
    }
}
