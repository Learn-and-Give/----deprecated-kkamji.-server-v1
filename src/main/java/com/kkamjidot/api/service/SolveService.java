package com.kkamjidot.api.service;

import com.kkamjidot.api.repository.SolveRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SolveService {
    private final SolveRepository solveRepository;

    @Builder
    public boolean isSolved(Long quizId, Long memberId) {
        return solveRepository.existsByQuizIdAndMemberId(quizId, memberId);
    }
}
