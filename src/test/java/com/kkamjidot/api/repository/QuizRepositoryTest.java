package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("local")
public class QuizRepositoryTest {
    @Autowired
    QuizRepository quizRepository;

    @Test
    public void 테스트() {
        System.out.println("quizRepository = " + quizRepository.findAll());
    }
}
