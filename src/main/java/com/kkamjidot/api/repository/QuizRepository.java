package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.Quizbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByQuizbookIdOrderByQuizNumber(Long quizbookId);   // 문제집 아이디로 문제들 조회

    Optional<Quiz> findByQuizbookIdAndQuizNumber(Long quizbookId, Integer quizNumber);   // 문제집과 문제 번호로 문제 조회
}