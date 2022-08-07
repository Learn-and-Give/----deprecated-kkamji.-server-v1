package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.Quizbook;
import com.kkamjidot.api.domain.Solve;
import com.kkamjidot.api.repository.MemberRepository;
import com.kkamjidot.api.repository.QuizRepository;
import com.kkamjidot.api.repository.QuizbookRepository;
import com.kkamjidot.api.repository.SolveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SolveServiceTestInTest {
    @Autowired
    SolveService solveService;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizbookRepository quizbookRepository;

    @Test
    void solve() {
        // given
        memberRepository.save(Member.builder()
                .memberName("홍길동")
                .memberPassword("1234")
                .build());

        Quizbook quizbook = Quizbook.builder()
                .quizbookTitle("테스트책")
                .build();
        quizbookRepository.save(quizbook);

        quizRepository.save(Quiz.builder()
                    .quizContent("문제")
                    .quizAnswer("정답")
                        .quizbook(quizbook)
                .build());

        String code = "1234";
        Long quizId = 1L;
        boolean isCorrected = true;

        // when
        Solve result = solveService.solve(code, quizId, isCorrected);

        // then
        assertThat(result.getSolveIsCorrected()).isEqualTo(isCorrected);
        assertThat(result.getMember().getMemberName()).isEqualTo("홍길동");
        assertThat(result.getQuiz().getQuizContent()).isEqualTo("문제");
    }
}