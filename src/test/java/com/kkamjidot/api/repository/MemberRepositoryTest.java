package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 테스트")
    public void createMemberTest() {
        //given
        memberRepository.save(Member.builder()
                .name("홍길동")
                .password("1234")
                .build());

        //when
        List<Member> usersList = memberRepository.findAll();

        //then
        Member user = usersList.get(0);
        assertThat(user.getName()).isEqualTo("홍길동");
    }

    @Test
    @DisplayName("이름과 비밀번호로 회원 정보 조회 테스트")
    public void findByNameAndPasswordTest() {
        //given
        memberRepository.save(Member.builder()
                .name("홍길동")
                .password("1234")
                .build());

        //when
        Member member = memberRepository.findByNameAndPassword("홍길동", "1234").orElseThrow();

        //then
        assertThat(member.getName()).isEqualTo("홍길동");
    }
}