package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Solve;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
public class SolveServiceTestInLocal {
    @Autowired
    SolveService solveService;

    @Test
    void isSolvedByCode() {
        // given
        Long quizId = 2L;
        String code = "1234";

        // when
        boolean isSolve = solveService.isSolvedByCode(code, quizId);

        // then
        assertThat(isSolve).isEqualTo(true);
    }

    @Transactional
    @Test
    void 문제_풂_조회() {
        // given
        Long quizId = 2L;
        Long memberId = 1L;

        // when
        boolean isSolve = solveService.isSolved(memberId, quizId);

        // then
        assertThat(isSolve).isEqualTo(true);
    }
/*
    @Test
    void 풀다() {
        // given
        Long quizId = 2L;
        String code = "1234";
        boolean isCorrected = true;

        // when
        Solve solve = solveService.solve(code, quizId, isCorrected);

        // then
        assertThat(solve.getSolveIsCorrected()).isEqualTo(isCorrected);
    }
 */
}
