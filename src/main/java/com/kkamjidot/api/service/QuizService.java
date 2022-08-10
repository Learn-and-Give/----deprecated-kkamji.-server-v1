package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuizService {
    private final QuizRepository quizRepository;

    // 문제 모음 반환
    public List<Quiz> findQuizzes(Long quizbookId) throws NoSuchElementException {
        List<Quiz> quizzes = quizRepository.findAllByQuizbookIdOrderByQuizNumber(quizbookId);

        if (quizzes.isEmpty()) throw new NoSuchElementException("존재하지 않는 문제입니다.");

        return quizzes;
    }

    public Quiz findOne(Long quizId) throws NoSuchElementException {
        return quizRepository.findById(quizId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 문제입니다."));
    }
}
