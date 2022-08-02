package com.kkamjidot.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class SolveRepositoryTest {
    @Autowired
    SolveRepository solveRepository;

    @Test
    public void 퀴즈_풂_여부_확인() {
        // given
        Long memberId = 1L;
        Long quizId = 1L;

        // when
        boolean check = solveRepository.existsByQuizIdAndMemberId(quizId, memberId);

        // then
        assertThat(check).isTrue();
    }
}