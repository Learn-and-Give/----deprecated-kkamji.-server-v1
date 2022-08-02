package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Quizbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class QuizbookRepositoryTest {
    @Autowired
    QuizbookRepository quizbookRepository;

    @BeforeEach
    void setUp() {
        //given
        quizbookRepository.save(Quizbook.builder().quizbookWeek(1).build());
        quizbookRepository.save(Quizbook.builder().quizbookWeek(1).build());
        quizbookRepository.save(Quizbook.builder().quizbookWeek(1).build());
        quizbookRepository.save(Quizbook.builder().quizbookWeek(2).build());
        quizbookRepository.save(Quizbook.builder().quizbookWeek(2).build());
        quizbookRepository.save(Quizbook.builder().quizbookWeek(3).build());
    }

    @AfterEach
    void tearDown() {
        quizbookRepository.deleteAll();
    }

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
        assertThat(quizbooks1.size()).isEqualTo(3);
        assertThat(quizbooks2.size()).isEqualTo(2);
        assertThat(quizbooks3.size()).isEqualTo(1);
    }
}