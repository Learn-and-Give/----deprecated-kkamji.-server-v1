package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Quizbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizbookRepository extends JpaRepository<Quizbook, Long> {
    List<Quizbook> findAllByChapterId(Long chapterId);    // 주차별 문제집 모음 조회

    Optional<Quizbook> findById(Long id);    // 주차별 문제집 조회
}