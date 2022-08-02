package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.dto.response.QuizSummaryResponseDto;
import com.kkamjidot.api.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuizService {
    private final QuizRepository quizRepository;

    // 문제 개요 반환
    public QuizSummaryResponseDto findQuizSummaryByQuizId(Long quizId) {
        // 문제 정보를 가져온 후, 응답 객체에 담아서 반환
        return new QuizSummaryResponseDto(quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문제입니다.")));

    }

    // 문제집 내 문제 목록 반환
    public List<Quiz> findQuizSummaryByQuizbookId(Long quizbookId) {
        List<Quiz> quizs = quizRepository.findByQuizbookId(quizbookId);

        if (quizs.isEmpty()) throw new IllegalArgumentException("문제집에 문제가 없습니다.");
        return quizs;
    }

    public Quiz findQuizById(Long quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문제입니다."));
    }
}
