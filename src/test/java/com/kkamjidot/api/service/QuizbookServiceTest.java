package com.kkamjidot.api.service;

import com.kkamjidot.api.dto.response.QuizbookResponseDto;
import com.kkamjidot.api.repository.MemberRepository;
import com.kkamjidot.api.repository.QuizbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class QuizbookServiceTest {
    @Autowired
    QuizbookService quizbookService;

    @Autowired
    QuizbookRepository quizbookRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 주차별_문제집_모음_조회() {
//        //given
//        memberRepository.save(Member.builder()
//                .memberName("홍길동")
//                .memberPassword("1234")
//                .build());
//
//        memberRepository.save(Member.builder()
//                .memberName("김양반")
//                .memberPassword("1111")
//                .build());
//
//        memberRepository.save(Member.builder()
//                .memberName("흥부")
//                .memberPassword("2222")
//                .build());
//
//        quizbookRepository.save(Quizbook.builder()
//                        .quizbookTitle("주차1 문제집1 제목")
//                        .quizbookDescription("주차1 문제집1 설명")
//                        .quizbookWeek(1)
//                        .member(memberRepository.findById(1L).get())
//                        .build());
//
//        quizbookRepository.save(Quizbook.builder()
//                .quizbookTitle("주차1 문제집2 제목")
//                .quizbookDescription("주차1 문제집2 설명")
//                .quizbookWeek(1)
//                .member(memberRepository.findById(2L).get())
//                .build());
//
//        quizbookRepository.save(Quizbook.builder()
//                .quizbookTitle("주차2 문제집1 제목")
//                .quizbookDescription("주차2 문제집1 설명")
//                .quizbookWeek(2)
//                .member(memberRepository.findById(3L).get())
//                .build());

        //when//
        //when
        List<QuizbookResponseDto> quizbooks = quizbookService.findQuizbooksByWeek(1);

        //then
        System.out.println("quizbooks = " + quizbooks);

    }
}