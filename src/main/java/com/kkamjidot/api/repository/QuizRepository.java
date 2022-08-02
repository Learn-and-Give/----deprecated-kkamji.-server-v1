package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}