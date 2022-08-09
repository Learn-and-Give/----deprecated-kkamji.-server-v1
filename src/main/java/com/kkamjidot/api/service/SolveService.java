//package com.kkamjidot.api.service;
//
//import com.kkamjidot.api.domain.Member;
//import com.kkamjidot.api.domain.Quiz;
//import com.kkamjidot.api.domain.Solve;
//import com.kkamjidot.api.repository.MemberRepository;
//import com.kkamjidot.api.repository.QuizRepository;
//import com.kkamjidot.api.repository.SolveRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.management.openmbean.KeyAlreadyExistsException;
//
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//@Service
//public class SolveService {
//    private final SolveRepository solveRepository;
//    private final MemberRepository memberRepository;
//    private final QuizRepository quizRepository;
//
//    // 문제 풀었는지 아닌지 여부 반환
//    public boolean isSolved(Long memberId, Long quizId) {
//        quizRepository.findById(quizId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문제입니다."));     // 없는 문제에 대한 예외 처리
//        return solveRepository.existsByMemberIdAndQuizId(memberId, quizId);
//    }
//
//    public boolean isSolvedByCode(String code, Long quizId) {
//        return solveRepository.existsByMemberMemberPasswordAndQuizId(code, quizId);
//    }
//
//    // 문제 풀기
//    @Transactional
//    public Solve solve(String code, Long quizId, boolean isCorrected) throws IllegalStateException, IllegalArgumentException {
//        if (isSolvedByCode(code, quizId)) throw new KeyAlreadyExistsException("이미 풀었습니다.");
//
//        Member member = memberRepository.findByMemberPassword(code).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
//        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문제입니다."));
//
//        return solveRepository.save(Solve.builder()
//                        .solveIsCorrected(isCorrected)
//                        .quiz(quiz)
//                        .member(member)
//                        .build());
//    }
//}
