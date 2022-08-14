//package com.kkamjidot.api.repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ActiveProfiles("local")
//class SolveRepositoryTest {
//    @Autowired
//    SolveRepository solveRepository;
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    QuizRepository quizRepository;
//    @Test
//    public void 퀴즈_풂_여부_확인() {
//        // given
//        Long memberId = 1L;
//        Long quizId = 1L;
//
//        // when
//        boolean check = solveRepository.existsByMemberIdAndQuizId(memberId, quizId);
//
//        // then
//        assertThat(check).isTrue();
//    }
//
////    @Transactional(readOnly = true)
//    @Test
//    void findByQuizIdAndMemberMemberPassword() {
//        // given
//        Long quizId = 1L;
//        String memberPassword = "1234";
//
//        // when
//        boolean isSolve = solveRepository.existsByMemberMemberPasswordAndQuizId(memberPassword, quizId);
//
//        // then
//        assertThat(isSolve).isEqualTo(true);
//    }
//
//    @Test
//    void 풀기() {
//        solveRepository.save(Solve.builder()
//                .solveIsCorrected(true)
////                .createdDate(Instant.now())
//                .quiz(quizRepository.findById(2L).get())
//                .member(memberRepository.findById(1L).get())
//                .build());
//    }
//}