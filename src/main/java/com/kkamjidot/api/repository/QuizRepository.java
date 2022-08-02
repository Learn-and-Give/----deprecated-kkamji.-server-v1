package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByQuizbookId(Long quizbookId);   // 문제집 아이디로 문제들 조회
}