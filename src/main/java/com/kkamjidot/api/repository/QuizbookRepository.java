//package com.kkamjidot.api.repository;
//
//import com.kkamjidot.api.domain.Quizbook;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface QuizbookRepository extends JpaRepository<Quizbook, Long> {
//    List<Quizbook> findByQuizbookWeek(int week);    // 주차별 문제집 모음 조회
//}