package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Solve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolveRepository extends JpaRepository<Solve, Long> {
    boolean existsByMemberIdAndQuizId(Long memberId, Long quizId);

    boolean existsByMemberMemberPasswordAndQuizId(String memberPassword, Long quizId);
}