package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Quizbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(properties = { "spring.config.location=classpath:application-local.yml" })
@SpringBootTest
class QuizbookRepositoryTest2 {
    @Autowired
    QuizbookRepository quizbookRepository;

    @Test
    @DisplayName("주차별 문제집 모음 조회")
    void findByWeek() {
        //given
        int week1 = 1;
        int week2 = 2;
        int week3 = 3;
        
        //when
        List<Quizbook> quizbooks1 = quizbookRepository.findByQuizbookWeek(week1);
        List<Quizbook> quizbooks2 = quizbookRepository.findByQuizbookWeek(week2);
        List<Quizbook> quizbooks3 = quizbookRepository.findByQuizbookWeek(week3);

        //then
        assertThat(quizbooks1.size()).isEqualTo(4);
    }
}