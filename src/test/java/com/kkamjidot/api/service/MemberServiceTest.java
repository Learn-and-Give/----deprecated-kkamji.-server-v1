package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.repository.MemberRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void cleanup() {
        memberRepository.deleteAll();
    }


    @Test
    @DisplayName("로그인 성공 테스트")
    void loginSuccess() {
        //given
        memberRepository.save(Member.builder()
                .name("홍길동")
                .password("1234")
                .build());

        //when
        Member member = memberService.login("홍길동", "1234");

        //then
        assertThat(member.getName()).isEqualTo("홍길동");
        assertThat(member.getPassword()).isEqualTo("1234");
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void loginFail() {
        //given
        memberRepository.save(Member.builder()
                .name("홍길동")
                .password("1234")
                .build());

        //when, then
        assertThatThrownBy(() -> memberService.login("홍길동", "1235"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("존재하지 않는 회원입니다.");
    }
}