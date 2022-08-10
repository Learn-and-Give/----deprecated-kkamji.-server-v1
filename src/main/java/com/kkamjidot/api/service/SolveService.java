package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.Solve;
import com.kkamjidot.api.repository.SolveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SolveService {
    private final SolveRepository solveRepository;

//    @PersistenceContext
//    EntityManager em;

    // 문제 풀기
    @Transactional
    public Solve saveSolve(Quiz quiz, Member member, Boolean solveIsCorrect) throws KeyAlreadyExistsException {
        if (quiz.getIsSolved(member)) throw new KeyAlreadyExistsException("이미 풀었던 문제입니다.");
        return solveRepository.save(Solve.builder()
                        .solveIsCorrect(solveIsCorrect)
                        .quiz(quiz)
                        .member(member)
                        .build());

//        em.flush();
//        em.clear();

//        return solveRepository.findById(solve.getId()).get();
    }
}
